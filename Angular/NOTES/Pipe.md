```ts
<li>{{ price | currency: 'INR' }} </li>
<li> {{ date | date: 'fullDate' | uppercase }} </li>

# Generate Custom Pipe:
    ng generate pipe pipeName
    ng g pipe pipeName

# Custom Pipe:
// percentage.pipe.ts
import { Pipe, PipeTransform } from '@angular/core';
@Pipe({
    name: 'percentage'
    //  pure: false // Default is true
})
export class PercentagePipe implements PipeTransform {
    transform(value: number, totalMarks: number = 100, decimal?: number): any {
        return ((value / totalMarks) * 100).toFixed(decimal || 2) + '%';
    }
}

// AppModule.ts:
@NgModule({
    declarations: [
        PercentagePipe
    ], …
})
export class AppModule { }

// app.component.html:
<li> {{ marks | percentage }}</li>
<li> {{ 390 | percentage: 500 }}</li> / / 78
<li> {{ 392.234 | percentage: 500: 4 }}</li>  / / 78.4468

# Pure Pipe:
- Pure pipe executes only when a pure change to the input value is detected.
- Changing the reference, not the value.
- eg. array = [1, 2, 3]; array = [1, 2, 3, 4];

# Impure Pipe:
- Impure pipe executes whenever a change is detected in the input value.
- Exeutes on every change detection cycle. It can slow down the application performance.
- Called when Reference or Value changes.
- eg. array[0].name = 'newName';
  array.push(4);
  @Pipe({ name: 'pipeName', pure: false })

# Async Pipe:
- It is used to subscribe to an observable or promise and return the latest value it has emitted.
- eg. {{students}}   // [object Promise]
  {{students | async}} // [{id: 1, name: 'John'}, ...]



```ts