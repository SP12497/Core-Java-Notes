# Comprehensive Notes on CSS (Cascading Style Sheets)

CSS (Cascading Style Sheets) is a stylesheet language used to describe the presentation of a document written in HTML or XML. It controls the layout, design, and visual formatting of web pages, enhancing user experience.

---

## Basics of CSS

### Types of CSS:
1. **Inline CSS**:
   - Applied directly to an HTML element using the `style` attribute.
   - Example:
     ```html
     <p style="color: blue; font-size: 14px;">This is inline CSS.</p>
     ```

2. **Internal CSS**:
   - Defined within a `<style>` tag in the `<head>` section of an HTML document.
   - Example:
     ```html
     <style>
       p {
         color: green;
         font-size: 16px;
       }
     </style>
     ```

3. **External CSS**:
   - Written in a separate `.css` file and linked using the `<link>` tag in the HTML document.
   - Example:
     ```html
     <link rel="stylesheet" href="styles.css">
     ```

---

## CSS Syntax
```css
selector {
  property: value;
}
```
- **Selector**: Specifies the HTML element(s) to style.
- **Property**: Specifies the style attribute (e.g., `color`, `margin`).
- **Value**: Defines the value for the property (e.g., `red`, `10px`).

Example:
```css
h1 {
  color: red;
  font-size: 24px;
}
```

---

## Selectors in CSS

### Basic Selectors:
1. **Universal Selector** (`*`): Selects all elements.
   ```css
   * {
     margin: 0;
     padding: 0;
   }
   ```
2. **Type Selector**: Selects all elements of a specific type (e.g., `p`, `h1`).
   ```css
   p {
     font-size: 14px;
   }
   ```
3. **Class Selector** (`.`): Selects elements with a specific class.
   ```css
   .my-class {
     color: blue;
   }
   ```
4. **ID Selector** (`#`): Selects elements with a specific ID.
   ```css
   #my-id {
     background-color: yellow;
   }
   ```

### Advanced Selectors:
1. **Group Selector**: Applies the same styles to multiple selectors.
   ```css
   h1, h2, h3 {
     color: green;
   }
   ```
2. **Descendant Selector**: Styles elements within a specific hierarchy.
   ```css
   div p {
     font-size: 12px;
   }
   ```
3. **Child Selector** (`>`): Selects direct child elements.
   ```css
   div > p {
     color: red;
   }
   ```
4. **Adjacent Sibling Selector** (`+`): Selects an element immediately following another.
   ```css
   h1 + p {
     font-weight: bold;
   }
   ```
5. **Attribute Selector**: Selects elements based on attributes.
   ```css
   input[type="text"] {
     border: 1px solid black;
   }
   ```

---

## Box Model
The CSS box model consists of the following components:
1. **Content**: The content of the element.
2. **Padding**: Space between the content and the border.
3. **Border**: Surrounds the padding.
4. **Margin**: Space outside the border.

### Example:
```css
p {
  margin: 20px;
  padding: 10px;
  border: 2px solid black;
  width: 200px;
}
```

### Visual Representation:
```
| Margin |
| Border |
| Padding |
| Content |
```

---

## Positioning in CSS

1. **Static** (default): Elements are positioned according to the normal flow of the document.
2. **Relative**: Position relative to its normal position.
   ```css
   div {
     position: relative;
     top: 10px;
   }
   ```
3. **Absolute**: Positioned relative to the nearest positioned ancestor.
   ```css
   div {
     position: absolute;
     top: 50px;
     left: 50px;
   }
   ```
4. **Fixed**: Positioned relative to the viewport (doesn’t move on scroll).
   ```css
   div {
     position: fixed;
     top: 0;
   }
   ```
5. **Sticky**: Toggles between relative and fixed based on scroll.
   ```css
   div {
     position: sticky;
     top: 20px;
   }
   ```

---

## Responsive Design

### Media Queries
Used to apply styles based on device screen size or other properties.

Example:
```css
@media (max-width: 600px) {
  body {
    background-color: lightblue;
  }
}
```

### Units for Responsive Design:
1. Relative units: `%`, `em`, `rem`, `vh`, `vw`
2. Absolute units: `px`, `cm`, `mm`

---

## Commonly Used CSS Properties

### Text Styling:
- `color`, `font-size`, `font-family`, `text-align`, `line-height`, `text-transform`

### Background:
- `background-color`, `background-image`, `background-size`, `background-position`, `background-repeat`

### Borders:
- `border`, `border-radius`, `border-style`

### Box Shadow:
```css
box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);
```

### Transitions:
```css
transition: all 0.3s ease;
```

### Flexbox:
- `display: flex;`, `justify-content`, `align-items`, `flex-direction`

---

## CSS Interview Questions

### Basic Questions:
1. What are the different types of CSS?
2. Explain the box model.
3. What is the difference between `relative`, `absolute`, and `fixed` positioning?
4. How do you apply styles to a specific element?

### Advanced Questions:
1. What is the difference between `em` and `rem`?
2. How does Flexbox work? Provide examples.
3. What are pseudo-classes and pseudo-elements?
4. Explain the difference between `inline`, `block`, and `inline-block` elements.

### Practical Tasks:
1. Create a responsive navigation bar.
2. Implement a card layout using Flexbox or Grid.
3. Style a form with hover and focus effects.
4. Create a tooltip using only CSS.

---

## Conclusion
CSS is essential for creating visually appealing and responsive web applications. Mastering CSS selectors, properties, and responsive design techniques is crucial for front-end development. Practice real-world examples and keep exploring advanced concepts like animations, Flexbox, and Grid to excel in interviews and projects.

