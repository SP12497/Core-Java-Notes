```ts
# Template Driven Form:
- In Template-driven forms, we write logic and controls in the HTML template.
- Template-driven forms are template-driven forms.
- Template-driven forms are more suitable for simple forms.

- Angular uses directives to create and manage forms.
- ngForm: Directive that creates a top-level FormGroup instance and binds it to a form element.
- ngModel: Directive that creates a FormControl instance from a domain model and binds it to a form control element.
- ngModelGroup: Directive that creates a FormGroup instance from a domain model and binds it to a form group element.
- ngSubmit: Event that is emitted when the user submits the form.
- ngModelChange: Event that is emitted when the value of the control changes.
- ngModelOptions: Directive that configures the behavior of a form control.

// HTML IMP: ngForm, ngModel, name

<form (ngSubmit)="onSubmit(myForm)" #myForm="ngForm">
  <input type="text" name="name" ngModel required>	// Get Control: add name and ngModel to the input element
  <input type="text" name="email" [ngModel]="a@b.com" email> // email validation with default value
  <div ngModelGroup="address"> // Grouping
	<input type="text" name="street">
	<input type="text" name="city">
  </div>
  <button type="submit">Submit</button>
</form>

onSubmit(form: NgForm) {
  console.log(form.value);  // {name: 'value'}
  console.log(form.valid);  // true
}
---
<form (ngSubmit)="onSubmit(myForm)" #myForm="ngForm"> // ngSubmit() argument not required
	<input type="submit" value="Submit" [disabled]="!myForm.valid"> // Disable Submit button if form is invalid
</form>
@ViewChild('myForm') myForm: NgForm; // Get Control: add ViewChild() to the component class
onSubmit() {
  console.log(this.myForm.value);  // {name: 'value'}
  console.log(this.myForm.valid);  // true
  console.log(this.myForm.controls['name'].value);  // value
  console.log(this.myForm.controls['address'].controls.city.value);  // value
}
---
Classes:
ng-touched: The control has been visited.
ng-untouched: The control has not been visited.
ng-pristine: The control's value has not changed.
ng-dirty: The control's value has changed.
ng-valid: The control's value is valid.
ng-invalid: The control's value is invalid.
ng-pending: The control's validation is pending.

.ng-invalid { border: 1px solid red; } // Style invalid input elements
---
SetValue():
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
PatchValue():
- can have partial form structure
- Used to update values
this.myForm.patchValue({
  name: 'Sagar',
  address: {
	city: 'Pune'
  }
});
---
- valueChanges: Observable that emits an event every time the value of the form changes
this.myForm.valueChanges.subscribe((value) => {
  console.log(value);
});
- statusChanges: Observable that emits an event every time the status of the form changes
this.myForm.statusChanges.subscribe((status) => {
  console.log(status);
});

---
<!-- Form Status -->
{{ myForm.status }} // VALID, INVALID, PENDING, DISABLED
{{ myForm.touched }} // true, false
{{ myForm.dirty }} // true, false
{{ myForm.pristine }} // true, false
{{ myForm.errors }} // {required: true}
{{ myForm.controls['name'].status }} // VALID, INVALID, PENDING
---
- Reset form:
this.myForm.reset(); // Reset form
this.myForm.reset({name: 'Sagar'}); // Reset form with default values
this.myForm.address.reset(); // Reset only address group

```