# Data Binding in Angular

Data binding in Angular is a mechanism that enables seamless communication between the template (HTML) and the component (TypeScript). It allows the application to update the view whenever the data changes, and vice versa.

Angular provides **four types of data binding techniques**, which can be broadly categorized into:

1. **Interpolation** (One-way binding)
2. **Property Binding** (One-way binding)
3. **Event Binding** (One-way binding)
4. **Two-way Binding**

## 1. Interpolation (One-way Binding)
Interpolation is used to display dynamic data from the component in the template. It uses the `{{ }}` syntax to bind data from the component to the view.

### Example:
```html
<h1>{{ title }}</h1>
<p>{{ getDescription() }}</p>
```

### Component:
```typescript
export class AppComponent {
  title = 'Welcome to Angular';

  getDescription() {
    return 'This is an example of interpolation in Angular.';
  }
}
```

### Explanation:
- The `title` property and `getDescription()` method are accessed directly in the template using `{{ }}`.
- Angular automatically updates the view whenever the `title` property changes.

---

## 2. Property Binding (One-way Binding)
Property binding is used to bind component data to the HTML element properties. It uses the `[property]="expression"` syntax.

### Example:
```html
<img [src]="imageUrl" [alt]="imageDescription">
```

### Component:
```typescript
export class AppComponent {
  imageUrl = 'https://angular.io/assets/images/logos/angular/angular.png';
  imageDescription = 'Angular Logo';
}
```

### Explanation:
- The `src` and `alt` attributes of the `<img>` element are dynamically updated based on the component's properties.
- Unlike interpolation, property binding works directly with DOM properties.

---

## 3. Event Binding (One-way Binding)
Event binding allows you to capture user actions, such as clicks, input, or other events, and execute methods in the component. It uses the `(event)="expression"` syntax.

### Example:
```html
<button (click)="onClick()">Click Me</button>
```

### Component:
```typescript
export class AppComponent {
  onClick() {
    alert('Button clicked!');
  }
}
```

### Explanation:
- The `click` event is bound to the `onClick()` method in the component.
- When the button is clicked, the method executes, displaying an alert.

### Example with Input:
```html
<input (input)="onInput($event)">
```

```typescript
export class AppComponent {
  onInput(event: Event) {
    const inputValue = (event.target as HTMLInputElement).value;
    console.log(inputValue);
  }
}
```

### Types of Events in Event Binding
Angular supports various types of DOM events that can be captured and handled using event binding:

1. **Mouse Events**
   - `click`, `dblclick`, `mousedown`, `mouseup`, `mouseover`, `mouseout`, `mousemove`

   **Example:**
   ```html
   <button (click)="onClick()">Click Me</button>
   ```

2. **Keyboard Events**
   - `keydown`, `keyup`, `keypress`

   **Example:**
   ```html
   <input (keydown)="onKeyDown($event)">
   ```

3. **Focus and Blur Events**
   - `focus`, `blur`

   **Example:**
   ```html
   <input (focus)="onFocus()" (blur)="onBlur()">
   ```

4. **Input Events**
   - `input`, `change`

   **Example:**
   ```html
   <input (input)="onInput($event)">
   ```

5. **Form Events**
   - `submit`, `reset`

   **Example:**
   ```html
   <form (submit)="onSubmit($event)">
     <button type="submit">Submit</button>
   </form>
   ```

6. **Touch Events** (For touch-enabled devices)
   - `touchstart`, `touchmove`, `touchend`

   **Example:**
   ```html
   <div (touchstart)="onTouchStart($event)"></div>
   ```

7. **Drag and Drop Events**
   - `drag`, `dragstart`, `dragend`, `dragover`, `drop`

   **Example:**
   ```html
   <div (dragstart)="onDragStart($event)"></div>
   ```

---

## 4. Two-way Binding
Two-way binding combines both property binding and event binding, allowing data to flow in both directions between the component and the view. It uses the `[(ngModel)]="expression"` syntax and requires importing the `FormsModule` in your Angular module.

### Example:
```html
<input [(ngModel)]="name">
<p>Hello, {{ name }}!</p>
```

### Component:
```typescript
export class AppComponent {
  name = '';
}
```

### Explanation:
- The `name` property is bound to both the input element and the template using `[(ngModel)]`.
- Any change in the input updates the `name` property, and vice versa.

### Module Configuration:
```typescript
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, FormsModule],
  bootstrap: [AppComponent]
})
export class AppModule {}
```

---

## Comparison of Data Binding Techniques

| Technique           | Direction of Data Flow | Syntax                | Example                     |
|---------------------|------------------------|-----------------------|-----------------------------|
| Interpolation       | Component to View      | `{{ expression }}`    | `<p>{{ title }}</p>`        |
| Property Binding    | Component to View      | `[property]="expr"`   | `<img [src]="imageUrl">`   |
| Event Binding       | View to Component      | `(event)="method"`    | `<button (click)="onClick()">` |
| Two-way Binding     | Both                   | `[(ngModel)]="expr"` | `<input [(ngModel)]="name">` |

---

## Key Points to Remember
1. **Interpolation**: Only works with strings and binds to the inner text of HTML elements.
2. **Property Binding**: Works directly with DOM properties, not just inner text.
3. **Event Binding**: Captures user actions and triggers methods in the component.
4. **Two-way Binding**: Simplifies synchronization between the component and the template but requires `FormsModule`.

Data binding is a core concept in Angular that facilitates seamless interaction between the component logic and the UI, making applications more dynamic and interactive.

