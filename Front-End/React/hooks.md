``` react.js

/**
 * React Hook
 *
 * React Hooks are functions that let you use state and other React features in functional components.
 * They allow you to "hook into" React state and lifecycle features from function components.
 * Common hooks include useState, useEffect, useContext, useReducer, and useRef.
 *
 * Example:
 * const [state, setState] = useState(initialState);
 *
 * Hooks must be called at the top level of a component or custom hook, not inside loops, conditions, or nested functions.
 */

1. useState(initialState: any): [any, (newState: any) => void];
    - used to manage state in functional components. 
    - const [count, setCount] = useState(initialState);
      setCount ((prev) => prev =+ 1);
      <p>You clicked {count} times</p>
      <button onClick={() => setCount(count + 1)}>Click me</button>

2. useEffect(callback: () => void, dependencies: any[]): void;
    - called whenever the page re-render
    - used to fetch data from an API, run side effects, or update the DOM.
    - always use inside the main method, not inside the if statement or sub function.

	- useEffect(() => {})	// execute whenever any state changes/render
	- useEffect(() => {}, [])	// execute only first state changes/render
    - useEffect(() => {
        document.title = `You clicked ${count} times`;
        console.log(document.title);
      }, [count]);  // Only re-run the effect if count changes
    
3. useContext:
    - Issue:
        - In below code, we have to always pass the request values in all the child components.
        - and if only child is re-rendering, it will also render the parent to get the latest value. This impact on the performance.
        - export const ParentComponent = () => {
            const [toggle, setToggle] = useState(false);
            <div>
                <ChildToggle setToggle={setToggle}></>
                <ChildDisplay toggle={toggle}> </>
            </div>
        }
        const ChildToggle = () = { return <>...</>}
    - Solution:
        - Pass the shared values in the context and use by useContext hook in child component.
        - 
            export const GlobalStateContext = createContext(null);
            export const ParentComponent = () => {

            }
            const [toggle, setToggle] = useState(false);
        

4. useReducer:
    - same as useState hook, but used to manager complex states.
    - many state alter based on action.
    - const reducer = (state, action) => {
        swithc(action.type) {
            case "INCREMENT":
                return { count: state.count+1, showText: state.showText }
            case "TOGGLE":
                return { count: state.count, showText: !state.showText }
            default:
                return state;
        }
    }

    const [state, dispatch] = useReducer(reducer, {count:0, showText: true})

    <h1> {state.count} </h1>
    <button
        onClick={()=>{ dispatch({ type: "INCREMENT" })  }}>
    <button
        onClick={()=>{ dispatch({ type: "TOGGLE" })  }}>

5.


```