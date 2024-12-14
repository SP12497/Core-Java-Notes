# Angular Directives: A Comprehensive Guide

Angular directives are a core feature of Angular that allow you to extend HTML's capabilities by creating custom behaviors, transforming the DOM, or managing templates. Directives are instructions for the Angular compiler to manipulate DOM elements, attributes, or even component behavior.

## Types of Directives

Angular directives are classified into three main types:

1. **Components (Special Type of Directive)**
2. **Structural Directives**
3. **Attribute Directives**

---

## 1. Components

A component is a directive with a template. It controls a part of the user interface and is the most commonly used directive in Angular.

### Characteristics of Components:

- Has a template (HTML).
- Defines a selector to identify the directive.
- Controls its own view.

### Example:

**Component Code:**

```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-hello',
  template: `<h1>Hello, {{name}}!</h1>`,
  styles: ['h1 { font-family: Lato; }']
})
export class HelloComponent {
  name = 'Angular';
}
```

**Usage in HTML:**

```html
<app-hello></app-hello>
```

---

## 2. Structural Directives

Structural directives change the DOM layout by adding or removing DOM elements. They are prefixed with an asterisk (`*`).

### Built-in Structural Directives:

1. **`*ngIf`**: Conditionally includes a template based on a boolean value.
2. **`*ngFor`**: Iterates over a collection and renders a template for each item.
3. **`*ngSwitch`**: Conditionally includes templates based on matching a value.

### Examples:

#### `*ngIf`

**Code:**

```html
<div *ngIf="isLoggedIn">Welcome back, user!</div>
<div *ngIf="!isLoggedIn">Please log in.</div>
```

**Component:**

```typescript
export class AppComponent {
  isLoggedIn = true;
}
```

#### `*ngFor`

**Code:**

```html
<ul>
  <li *ngFor="let item of items">{{item}}</li>
</ul>
```

**Component:**

```typescript
export class AppComponent {
  items = ['Apple', 'Banana', 'Cherry'];
}
```

#### `*ngSwitch`

**Code:**

```html
<div [ngSwitch]="day">
  <p *ngSwitchCase="'Monday'">Start of the week!</p>
  <p *ngSwitchCase="'Friday'">Weekend is here!</p>
  <p *ngSwitchDefault>It's just another day.</p>
</div>
```

**Component:**

```typescript
export class AppComponent {
  day = 'Friday';
}
```

---

## 3. Attribute Directives

Attribute directives change the appearance or behavior of an element, component, or another directive. They work by modifying the attributes of the host element.

### Built-in Attribute Directives:

1. **`ngClass`**: Adds or removes CSS classes dynamically.
2. **`ngStyle`**: Applies dynamic inline styles to an element.
3. **`[class]`** and `[style]`: Shortcut bindings for classes and styles.

### Examples:

#### `ngClass`

**Code:**

```html
<div [ngClass]="{ 'active': isActive, 'disabled': !isActive }">
  Toggle Me
</div>
```

**Component:**

```typescript
export class AppComponent {
  isActive = true;
}
```

#### `ngStyle`

**Code:**

```html
<div [ngStyle]="{ 'background-color': bgColor, 'color': textColor }">
  Styled Text
</div>
```

**Component:**

```typescript
export class AppComponent {
  bgColor = 'blue';
  textColor = 'white';
}
```

#### `[class]` and `[style]`

**Code:**

```html
<div [class.active]="isActive">Active State</div>
<div [style.color]="textColor">Dynamic Style</div>
```

**Component:**

```typescript
export class AppComponent {
  isActive = true;
  textColor = 'red';
}
```

---

## Custom Directives

You can also create custom structural or attribute directives to implement your own logic.

### Example: Custom Structural Directive

A custom structural directive allows you to manipulate the DOM structure. For example, creating a directive that renders an element only if a condition is true:

**Directive Code:**

```typescript
import { Directive, Input, TemplateRef, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appIf]'
})
export class IfDirective {
  @Input() set appIf(condition: boolean) {
    if (condition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }

  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
}
```

**Usage in HTML:**

```html
<p *appIf="isVisible">This paragraph is conditionally rendered.</p>
```

**Component:**

```typescript
export class AppComponent {
  isVisible = true;
}
```

### Explanation:

1. **Directive Selector**: `[appIf]` is the attribute that activates the directive.
2. **`TemplateRef`**: References the content inside the directive's element.
3. **`ViewContainerRef`**: Represents the container where the content is added or removed.
4. **Condition Handling**: The directive listens to the input condition and dynamically adds or removes the content based on the value.

---

### Example: Custom Attribute Directive

**Directive Code:**

```typescript
import { Directive, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef, private renderer: Renderer2) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.renderer.setStyle(this.el.nativeElement, 'background-color', 'yellow');
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.renderer.removeStyle(this.el.nativeElement, 'background-color');
  }
}
```

**Usage in HTML:**

```html
<p appHighlight>Hover over this text to see the effect.</p>
```

---

## Summary Table

| **Directive Type**   | **Purpose**                                  | **Example**               |
| -------------------- | -------------------------------------------- | ------------------------- |
| **Component**        | Controls a view and contains a template      | `<app-hello></app-hello>` |
| **Structural**       | Changes the structure of the DOM             | `*ngIf`, `*ngFor`         |
| **Attribute**        | Changes appearance or behavior of an element | `ngClass`, `ngStyle`      |
| **Custom Directive** | Implements custom behavior                   | `[appHighlight]`          |

---

## Conclusion

Directives in Angular are a powerful way to extend the functionality of your applications. By understanding and using the different types of directives effectively, you can create dynamic and reusable components, manipulate DOM structures, and enhance user experiences. Start with built-in directives and gradually explore custom directive creation to master Angular development!

