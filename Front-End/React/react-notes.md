# React Router — Interview Notes

## 1. Installation

```bash
npm install react-router
```

Depending on the React Router version/project setup, APIs may be imported from `react-router` or `react-router-dom`. Follow the version's current documentation and project template.

---

## 2. `createBrowserRouter`

```jsx
import {
  createBrowserRouter,
  RouterProvider,
} from "react-router";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "about",
        element: <About />,
      },
    ],
  },
]);
```

Render:

```jsx
createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
```

---

## 3. Nested Routes

```jsx
const router = createBrowserRouter([
  {
    path: "/",
    element: <Layout />,
    children: [
      {
        index: true,
        element: <Home />,
      },
      {
        path: "about",
        element: <About />,
      },
    ],
  },
]);
```

Layout:

```jsx
import { Outlet } from "react-router";

function Layout() {
  return (
    <>
      <Header />

      <main>
        <Outlet />
      </main>

      <Footer />
    </>
  );
}
```

`<Outlet />` renders the element belonging to the currently matched child route.

---

## 4. `index: true`

Instead of:

```jsx
{
  path: "",
  element: <Home />,
}
```

Prefer an index route:

```jsx
{
  index: true,
  element: <Home />,
}
```

It means:

> Render this route at the parent's path when no child path is specified.

---

## 5. `Link`

Use `Link` for client-side navigation.

```jsx
import { Link } from "react-router";

<Link to="/about">
  About
</Link>
```

This is generally preferable to:

```html
<a href="/about">
```

when performing internal SPA navigation.

---

## 6. `NavLink`

`NavLink` provides active-state information.

```jsx
<NavLink
  to="/about"
  className={({ isActive }) =>
    isActive ? "active" : "inactive"
  }
>
  About
</NavLink>
```

Correct template literal:

```jsx
className={({ isActive }) =>
  `nav-link ${isActive ? "active" : "inactive"}`
}
```

---

## 7. Dynamic Route Parameters

Route:

```jsx
{
  path: "users/:userId",
  element: <User />,
}
```

URL:

```text
/users/123
```

Component:

```jsx
import { useParams } from "react-router";

function User() {
  const { userId } = useParams();

  return <h1>User: {userId}</h1>;
}
```

---

## 8. Query Parameters

URL:

```text
/products?category=books&page=2
```

Using `useSearchParams`:

```jsx
import { useSearchParams } from "react-router";

function Products() {
  const [searchParams, setSearchParams] =
    useSearchParams();

  const category =
    searchParams.get("category");

  const page =
    searchParams.get("page");

  return (
    <div>
      Category: {category}
      Page: {page}
    </div>
  );
}
```

---

## 9. Programmatic Navigation

```jsx
import { useNavigate } from "react-router";

function Login() {
  const navigate = useNavigate();

  function handleLogin() {
    // login...
    navigate("/dashboard");
  }

  return (
    <button onClick={handleLogin}>
      Login
    </button>
  );
}
```

Go back:

```jsx
navigate(-1);
```

---

## 10. Route Loaders

A route can define a loader:

```jsx
const router = createBrowserRouter([
  {
    path: "/github",
    loader: async () => {
      const response = await fetch(
        "https://api.github.com/users/facebook"
      );

      if (!response.ok) {
        throw new Error("Failed to fetch user");
      }

      return response.json();
    },
    element: <Github />,
  },
]);
```

Read loader data:

```jsx
import { useLoaderData } from "react-router";

function Github() {
  const data = useLoaderData();

  return (
    <div>
      <h1>{data.name}</h1>
      <p>Followers: {data.followers}</p>
    </div>
  );
}
```

---

## 11. Loader Mental Model

```text
Navigation
    ↓
Matched route
    ↓
loader()
    ↓
Loader data
    ↓
Route component
```

Loaders can provide route data before the route component renders.

---

## 12. `createRoutesFromElements`

JSX route configuration:

```jsx
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
} from "react-router";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      path="/"
      element={<Layout />}
    >
      <Route
        index
        element={<Home />}
      />

      <Route
        path="about"
        element={<About />}
      />

      <Route
        path="users/:userId"
        element={<User />}
      />
    </Route>
  )
);
```

---

## 13. Router Interview Questions

### `Link` vs `<a>`

`Link` performs client-side routing; a normal anchor can trigger a document navigation.

### `useParams`

Reads dynamic URL parameters.

### `useSearchParams`

Reads/updates URL query parameters.

### `useNavigate`

Performs programmatic navigation.

### `Outlet`

Renders the matched child route inside a parent layout.

### Loader

Provides route-associated data loading.

### Why use nested routes?

They allow layouts and child route UI to share structure.

---

# 4.context-api.md

# React Context API — Interview Notes

## 1. The Prop Drilling Problem

Without Context:

```text
App
 ↓
ComponentA
 ↓
ComponentB
 ↓
ComponentC
 ↓
ComponentD
```

If only `ComponentD` needs the user:

```jsx
<App user={user}>
  <ComponentA user={user}>
    <ComponentB user={user}>
      <ComponentC user={user}>
        <ComponentD user={user} />
      </ComponentC>
    </ComponentB>
  </ComponentA>
</App>
```

The intermediate components are only forwarding the data.

This is commonly called **prop drilling**.

---

## 2. Creating Context

```jsx
import { createContext } from "react";

export const UserContext = createContext(null);
```

---

## 3. Providing Context

```jsx
import { useState } from "react";
import { UserContext } from "./UserContext";

export function UserProvider({ children }) {
  const [user, setUser] = useState(null);

  return (
    <UserContext.Provider
      value={{
        user,
        setUser,
      }}
    >
      {children}
    </UserContext.Provider>
  );
}
```

---

## 4. Consuming Context

```jsx
import { useContext } from "react";
import { UserContext } from "./UserContext";

function Profile() {
  const { user } = useContext(UserContext);

  if (!user) {
    return <p>Please log in</p>;
  }

  return <p>Welcome {user.username}</p>;
}
```

---

## 5. Wrapping the Application

```jsx
function App() {
  return (
    <UserProvider>
      <Login />
      <Profile />
    </UserProvider>
  );
}
```

---

## 6. Context Provider Value

Avoid unnecessarily creating a new object when provider performance matters:

```jsx
const value = useMemo(
  () => ({
    user,
    setUser,
  }),
  [user]
);
```

Then:

```jsx
<UserContext.Provider value={value}>
  {children}
</UserContext.Provider>
```

But don't blindly memoize every context value. Optimize when there is a meaningful rendering concern.

---

## 7. Context Re-render Concept

If a context provider's value changes, components consuming that context can re-render.

This is one reason large, frequently changing state is not always best placed into one giant Context.

For large applications, split contexts by concern or use a state-management solution when appropriate.

---

## 8. Context vs Props

### Props

Best for explicit component relationships:

```text
Parent → Child
```

### Context

Useful when many descendants need the same value:

```text
Provider
 ├── Component A
 │    └── Component B
 │         └── Component C
 └── Component D
```

---

## 9. Context Is Not Redux

Context:

* Part of React.
* Primarily provides values through a tree.
* Does not automatically solve all state-management problems.

Redux:

* External state-management architecture.
* Centralized store.
* Actions/reducers.
* Middleware.
* DevTools.
* Ecosystem.
* Selectors.
* RTK Query for server-state/API use cases.

---

# 5.redux-toolkit.md

# Redux Toolkit — Interview Notes

## 1. Why Redux?

Redux is a predictable state-management library.

Useful when application state:

* Is shared by many unrelated components.
* Has complex update logic.
* Needs predictable state transitions.
* Benefits from DevTools/time-travel-style debugging.
* Needs middleware or a structured state architecture.

Do not put every piece of local state into Redux.

---

## 2. Redux Toolkit

Redux Toolkit (RTK) is the recommended way to write Redux logic.

Install:

```bash
npm install @reduxjs/toolkit react-redux
```

---

# 3. Core Redux Concepts

```text
Component
   ↓
dispatch(action)
   ↓
Reducer
   ↓
Store state
   ↓
useSelector
   ↓
Component
```

---

# 4. Store

Typical:

```jsx
import { configureStore } from "@reduxjs/toolkit";
import todoReducer from "../features/todos/todoSlice";

export const store = configureStore({
  reducer: {
    todos: todoReducer,
  },
});
```

Important:

> An application normally has one Redux store. The store can contain many feature reducers/slices.

---

# 5. Slice

A slice usually contains:

* Slice name
* Initial state
* Reducer functions

```jsx
import {
  createSlice,
  nanoid,
} from "@reduxjs/toolkit";

const initialState = {
  todos: [],
};

const todoSlice = createSlice({
  name: "todos",
  initialState,

  reducers: {
    addTodo: {
      reducer(state, action) {
        state.todos.push(action.payload);
      },

      prepare(text) {
        return {
          payload: {
            id: nanoid(),
            text,
            completed: false,
          },
        };
      },
    },

    removeTodo(state, action) {
      state.todos = state.todos.filter(
        (todo) => todo.id !== action.payload
      );
    },

    toggleTodo(state, action) {
      const todo = state.todos.find(
        (todo) => todo.id === action.payload
      );

      if (todo) {
        todo.completed = !todo.completed;
      }
    },
  },
});

export const {
  addTodo,
  removeTodo,
  toggleTodo,
} = todoSlice.actions;

export default todoSlice.reducer;
```

---

# 6. Why Can RTK "Mutate" State?

This:

```jsx
state.todos.push(todo);
```

looks like mutation.

Redux Toolkit uses **Immer** internally, allowing reducers to write mutation-like code while producing immutable state updates.

This is valid RTK reducer code:

```jsx
state.count += 1;
```

---

# 7. Provider

Wrap the React application:

```jsx
import { Provider } from "react-redux";
import { store } from "./app/store";

createRoot(
  document.getElementById("root")
).render(
  <Provider store={store}>
    <App />
  </Provider>
);
```

---

# 8. `useSelector`

Read data:

```jsx
import { useSelector } from "react-redux";

function Todos() {
  const todos = useSelector(
    (state) => state.todos.todos
  );

  return (
    <ul>
      {todos.map((todo) => (
        <li key={todo.id}>
          {todo.text}
        </li>
      ))}
    </ul>
  );
}
```

---

# 9. `useDispatch`

Dispatch an action:

```jsx
import { useDispatch } from "react-redux";
import { addTodo } from "../features/todos/todoSlice";

function AddTodo() {
  const dispatch = useDispatch();

  return (
    <button
      onClick={() =>
        dispatch(addTodo("Learn Redux"))
      }
    >
      Add
    </button>
  );
}
```

---

# 10. Complete Redux Flow

```text
User clicks button
       ↓
dispatch(addTodo("Learn Redux"))
       ↓
Redux action
       ↓
todoSlice reducer
       ↓
Store updated
       ↓
useSelector detects relevant state change
       ↓
Component renders updated UI
```

---

# 11. Redux Action

An action is an object describing what happened.

Example:

```js
{
  type: "todos/addTodo",
  payload: {
    id: "123",
    text: "Learn Redux"
  }
}
```

RTK generates action creators automatically:

```jsx
addTodo("Learn Redux");
```

---

# 12. Reducer

A reducer receives:

```text
current state
+
action
```

and determines the next state.

Conceptually:

```jsx
function reducer(state, action) {
  // calculate next state
}
```

Reducers should be deterministic and should not perform arbitrary side effects.

---

# 13. Selectors

Instead of repeating:

```jsx
useSelector(
  (state) => state.todos.todos
);
```

create a selector:

```jsx
export const selectTodos = (state) =>
  state.todos.todos;
```

Use:

```jsx
const todos = useSelector(selectTodos);
```

Selectors become especially useful for derived data.

---

# 14. Redux Toolkit + Async Logic

RTK supports async workflows.

Example:

```jsx
import {
  createAsyncThunk,
  createSlice,
} from "@reduxjs/toolkit";

export const fetchTodos =
  createAsyncThunk(
    "todos/fetchTodos",
    async () => {
      const response = await fetch("/api/todos");

      if (!response.ok) {
        throw new Error("Failed to fetch todos");
      }

      return response.json();
    }
  );
```

Handle lifecycle:

```jsx
const todoSlice = createSlice({
  name: "todos",

  initialState: {
    items: [],
    status: "idle",
    error: null,
  },

  reducers: {},

  extraReducers: (builder) => {
    builder
      .addCase(
        fetchTodos.pending,
        (state) => {
          state.status = "loading";
        }
      )

      .addCase(
        fetchTodos.fulfilled,
        (state, action) => {
          state.status = "succeeded";
          state.items = action.payload;
        }
      )

      .addCase(
        fetchTodos.rejected,
        (state, action) => {
          state.status = "failed";
          state.error =
            action.error.message;
        }
      );
  },
});
```

---

# 15. RTK Query

RTK Query is Redux Toolkit's data-fetching and caching solution.

It can provide:

* Data fetching
* Caching
* Request deduplication
* Cache invalidation
* Loading/error states
* Generated React hooks

Conceptual:

```jsx
const {
  data,
  isLoading,
  error,
} = useGetTodosQuery();
```

RTK Query is especially useful for server state, while ordinary slices are often used for client/application state.

---

# 16. Context vs Redux Toolkit

| Context                    | Redux Toolkit               |
| -------------------------- | --------------------------- |
| Built into React           | External library            |
| Value propagation          | Structured state management |
| Simple shared state        | Complex shared state        |
| No Redux middleware        | Middleware ecosystem        |
| Provider-based             | Store-based                 |
| Fine for theme/user/config | Good for larger app state   |
| No built-in API cache      | RTK Query available         |

Neither is universally "better." Choose based on application requirements.

---

# 17. Zustand

Zustand is another state-management option.

Typical concept:

```jsx
const useCounterStore = create(
  (set) => ({
    count: 0,

    increment: () =>
      set((state) => ({
        count: state.count + 1,
      })),
  })
);
```

Component:

```jsx
function Counter() {
  const count = useCounterStore(
    (state) => state.count
  );

  const increment = useCounterStore(
    (state) => state.increment
  );

  return (
    <button onClick={increment}>
      {count}
    </button>
  );
}
```

---

# 18. Local State vs Context vs Redux vs Zustand

### Local state

Use when only one component/subtree needs the state.

```jsx
useState()
useReducer()
```

### Context

Use for values naturally shared through a tree.

```jsx
Theme
Locale
Current user
Configuration
```

### Redux Toolkit

Useful for structured, complex, application-wide state.

### Zustand

Useful when you want a lightweight external store with a simple API.

---

# 19. Redux Interview Questions

### Why Redux Toolkit instead of old Redux patterns?

RTK reduces boilerplate and provides recommended defaults such as `configureStore`, `createSlice`, Immer integration, middleware setup, and DevTools integration.

### What is a slice?

A feature-level Redux state definition containing initial state and reducers/actions.

### What does `configureStore` do?

Creates/configures the Redux store with sensible defaults and combines reducer configuration.

### What does `useSelector` do?

Reads selected data from the Redux store and subscribes the component to relevant updates.

### What does `useDispatch` do?

Returns the dispatch function used to send actions.

### Why is Redux state immutable?

Redux relies on predictable state transitions and reference-based change detection. RTK uses Immer to make immutable updates easier to write.

### One store or multiple stores?

A normal Redux application uses one store containing multiple feature reducers.

### Should all React state be in Redux?

No. Keep local UI state local when there is no reason to make it global.

```
```
