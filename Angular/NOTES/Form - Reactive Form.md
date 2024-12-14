```ts
# Reactive Form:
- Reactive forms are model-driven forms.
- Reactive forms are more scalable, reusable, and testable.
- Reactive forms are define structures in the component class.

- Angular uses directives to create and manage forms.
- FormGroup: Class that creates a top-level FormGroup instance and binds it to a form element.
- FormControl: Class that creates a FormControl instance from a domain model and binds it to a form control element.
- FormArray: Class that creates a FormArray instance from a domain model and binds it to a form array element.
- FormBuilder: Service that provides convenient methods for generating instances of FormControl, FormGroup, and FormArray.
- Validators: Class that provides a set of built-in validators that can be used to validate form controls.
- AbstractControl: Class that provides common properties and methods for form controls and groups.

Setting:
tsconfig.json: => "strict": false

Step 1: Import ReactiveFormsModule in the app.module.ts file.
    import { ReactiveFormsModule } from '@angular/forms';
    @NgModule({
    imports: [ReactiveFormsModule], ...
    })
Step 2: Create a FormGroup instance in the component class.
    import { FormGroup, FormControl, Validators } from '@angular/forms';
    export class AppComponent {
        myForm: FormGroup;
        constructor() {
            this.myForm = new FormGroup({
                name: new FormControl('Sagar', Validators.required),        // Default Value
                email: new FormControl('', [Validators.required, Validators.email]),
                address: new FormGroup({        // Nested FormGroup // Grouping
                    street: new FormControl(),
                    city: new FormControl()
                }), 
                skills: new FormArray([        // FormArray
                    new FormControl()
            });
        }
    }

Step 3: Bind the FormGroup instance to the form element in the template.
    // HTML IMP: formGroup, formControlName
    <form [formGroup]="myForm" (ngSubmit)="onSubmit()">         // formGroup        // Template Driven Form: (ngSubmit)="onSubmit(myForm)"
        <input type="text" formControlName="name">              // formControlName  // Template Driven Form: name, ngModel

        <input type="text" formControlName="email">
        <span *ngIf="myForm.get('email').hasError('required')">Email is required</span>     // Custom Error Message
        <span *ngIf="!myForm.get('email').valid">Email is invalid</span>

        <div formGroupName="address">
            <input type="text" formControlName="street">
            <input type="text" formControlName="city">
        </div>

        <div formArrayName="skills">
            <div *ngFor="let skill of myForm.get('skills').controls; let i=index">
                <input type="text" [formControlName]="i">
            </div>
        <button type="submit">Submit</button>
    </form>

Step 4: Handle the form submission in the component class.
    onSubmit() {
        console.log(this.myForm.value);  // {name: 'Sagar'}
        console.log(this.myForm.valid);  // true
    }

---
# Disable Submit Button:
    <form [formGroup]="myForm" (ngSubmit)="onSubmit()">
        <input type="submit" value="Submit" [disabled]="!myForm.valid">
    </form>

---
# Validators in Reactive Form:
    Validators.required
    Validators.email
    Validators.minLength(3)
    Validators.min(18)
    Validators.max(60)
    Validators.pattern('^[a-zA-Z]+$')
    Validators.compose([Validators.required, Validators.email])
    Validators.composeAsync([Validators.required, Validators.email])

---
Custom Validator / Custom Error Message:

    noSpaceAllowed(control: FormControl): { [s: string]: boolean } {
        if (control.value != null && control.value.indexOf(' ') >= 0) {
            return { noSpace: true };
        }
        return null;
    }

    this.myForm = new FormGroup({
        name: new FormControl('Sagar', [Validators.required, this.noSpaceAllowed]),
    });

    <span *ngIf="myForm.get('name').hasError('noSpace')">No space allowed</span>

---
# Async Validator:
    - Async validator must return an Observable or a Promise.
    - Angular does not provide any built-in async validators.
    - uses: send HTTP request to the server to check if the email is already registered.

    emailAsyncValidator(control: FormControl): Promise<any> | Observable<any> {
        const promise = new Promise<any>((resolve, reject) => {     // Suppose we call API and API returns response after 2 sec
            setTimeout(() => {
                if (control.value === 'sagar@patil.com') {
                    resolve({ emailTaken: true });
                } else {
                    resolve(null);
                }
            }, 2000);
        });
        return promise;
    }

    this.myForm = new FormGroup({
        email: new FormControl('', [Validators.required], this.emailAsyncValidator),
    });

---
# SetValue():       // same as Template Driven Form
    - must have complete form structure, else it will throw error
    - Used to set default values
    - issue: if we filled email input and called setValue() them email will be erased.

    this.myForm.setValue({
        name: 'Sagar',
        email: '',
        address: {
            street: '',
            city: 'Pune'
        }
    });

---
# PatchValue():    // same as Template Driven Form
    - can have partial form structure
    - Used to update values

    this.myForm.patchValue({
        name: 'Sagar',
        address: {
            city: 'Pune'
        }
    });
---
# valueChanges:     // same as Template Driven Form
    - Observable that emits an event every time the value of the form changes

    this.myForm.valueChanges.subscribe((value) => {
        console.log(value);
    });
    this.myForm.statusChanges.get('name').valueChanges.subscribe((value) => {
        console.log(value);
    });

---
# statusChanges:    // same as Template Driven Form
    - Observable that emits an event every time the status of the form changes
    - It will provide the status of form as Valid or Invalid while entering the data in form.
    - Whenever changing the data in Form, that time status change event will occur. And it will display the status of entire form.

    this.myForm.statusChanges.subscribe((status) => {
        console.log(status);
    });

CSS:
.myForm.INVALID { border: 1px solid red; } // Style invalid input elements
.myForm.PENDING { border: 1px solid yellow; } // Style pending input elements

---
# Form Reset:
    this.myForm.reset();    // Reset form to initial state
    this.myForm.reset({ name: 'Sagar' });    // Reset form to initial state with default values

---
# FormArray:
    - FormArray is used to manage an array of FormControl, FormGroup, or FormArray instances.
    - FormArray is a class that extends the AbstractControl class.
    - FormArray is used to create an array of form controls.
    - FormArray is used to create an array of form groups.
    - FormArray is used to create an array of form arrays.

    this.myForm = new FormGroup({
        skills: new FormArray([
            new FormControl('Angular', Validators.required),
            new FormControl('React', Validators.required),
            new FormControl('Vue', Validators.required)
        ])
    });

    <div formArrayName="skills">
        <div *ngFor="let skill of myForm.get('skills').controls; let i=index">
            <input type="text" [formControlName]="i">
        </div>
    </div>

    addSkill() {
        const control = new FormControl('', Validators.required);
        (<FormArray>this.myForm.get('skills')).push(control);
    }

    removeSkill(index: number) {
        (<FormArray>this.myForm.get('skills')).removeAt(index);
    }


```ts