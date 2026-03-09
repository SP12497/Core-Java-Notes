``` typescript

Remix:
    - Always server-side rendered
    - No static site generation (build-time pre-rendering)
    - Always requires host that supports server-side code execution

Next:
    - Optional server-side rendering supported
    - static site generation (at build time) supported
    - Deployment options: Static hosting vs server-side code execution
    

Create Project:
    - npx create-remix@1    // version 1
    - npx create-remix@latest       // set NODE_TLS_REJECT_UNAUTHORIZED=0

Run:
    - remix dev

Public folder:
    - Static files
app folder:
    - React components and routes
    - root.jsx: 
  
  
# Section 2: Remix Essentials - Core concepts

Routing:
    - /app/routes/index.jsx
        import { Link } from '@remix-run/react'
        <Link to="/demo"> Go to Demo Page </Link>       // my-domail.com/demo => /app/routes/demo.jsx
        <a href="/demo"> </a>

app/root.jsx :
    - main file
    - <Outlet /> : renders our routing files view/content

CSS:
    - /app/styles/main.css
    - /app/root.jsx or any sub file:
        - link main.css file to root.jsx file:
            - HTML way in Headers: <link rel="stylesheet" href="">
            - Remix:
             import styles from '~/styles/main.css';        // ~ : refers to /app folder
             export function links() {
                return [{rel: 'stylesheet', href: styles}]
             }

NavLink:  // RouterLink in Angular
    - provides css class => a.active {}
        <li className="nav-item">
            <NavLink to="/">Home </NavLink>
        </li>
        <li className="nav-item">
            <NavLink to="/notes">Notes </NavLink>
        </li>

        export defaullt MainNavigation;
    - parent file:
        <MainNavigation />

        or root.jsx:
            <body>
                <header>
                    <MainNavigation/>
                </header>
                <Outlet />      // add navigation above Outlet
            <body>

        - redirect from component:
            - import { redirect } from '@remix-run/node'
              return redirect('/notes');
        - navigate:
            import { useNavigate } from '@remix-run/react';
            const navigate = useNagigation();
            navigate("/about");
            navigate("..");
    - Link vs NavLink:
        - Navlink: to highlight selection
        - Link: it wont highlight selection
action function:
    - When we want to write server side code, which render on server side not on client side.
    - used inside routes components.
    - any method other that GET method, will trigger this method
    - export function action () { ...server side code}
    - export function action (data) { 
        const formData = data.request.formData();
        const noteData = {
            title: formData.get('title')
            content: formData.get('content')
        }
        // const noteData = Object.fromEntries(formData)   // this will extract all fields and create object

        // Validation:
        if(noteData.title.trim().length < 5) {
            // alert("error")   // we can't write browser code in action, because this will resolve on server side.
            return { message: 'Invalid title' }
        }

        return redirect('/notes');  // execute loader method again and displays the latest data
    }
    - Hook:
        - const data = useActionData() // this hook capure return data of actionn
        - <Form method="post">
            { data?.message && <p>{data.message}</p> }

loader method:
    - Execute whenever GET method is called.
    -   import { useLoaderData } from '@remix-run/react';   // hook
        export default function NotesPage() {
            const notes = useLoaderData();        // hook to capture json data from loader method.
            return (
                <main>
                    <NewNote />     // contains form to create note
                    <NoteList notes={notes} />  // display notes
                </main>
            )
        }
        export async function loader() {
            const notes = await getNotesFromDB();
            return notes;

            // return new Response(JSON.stringify(notes), {headers: {'content-type': 'application/json'}}) // node js Response method

            // return json(notes);  // remix response method
        }

Form:
    - <form method="post" id="note-form">  </form> // it will reload all data, once we added new data.    
    - <Form method="post" id="note-form"> </Form>  // it will only load new data, once we added new data. Prevent page being reloaded.   

useNavigation hook: // useTransition is older name
    - if we double click on submit button, still form will submit only once.
    - const navigation = useNagigation();
    //   navigation.state ==>   idle, loading, submitting
    //   navigation.submission. {action, encType, formData, key, method}
    //   navigation.type
    const isSubmitting = navigation.state = 'submitting';

    <button disabled={isSubmitting}>


ErrorBoundry:
    - create custom error page:
    - /app/root.jsx:
        export function ErrorBoundry({error} => {
            return (
                <html> ... </html>
            )
        })
    - called automatically, whenever any error occured
    - called manually: Whenever throw error message
        - loader() { throw "error" }

CatchBoundry:
    - called manually: Whenever throw response
        - loader() { throw json({message: "error occured", {status: 404}}) }
        - loader() { throw new Response(notes) }
    - export function CatchBoundry() {
        const caughtResponse = useCatch();  // useCatch: hook to catch error response.
        const message = caughtResponse.data?.message;
        return (
            <main>
                <p>{message}</p>
            </main>
        );
    }

Dynamic Routes:
    - file name:
        - /routes/$noteId.jsx   : eg. /notes-1
        - /routes/notes.$noteId.jsx   : eg. /notes/1
        - /routes/notes/$.noteId.jsx    : /notes/1
    - fetch patam value:
        loader(data) {data.params.noteId}

meta:
    - use to pass meta data to the component.
    - export function meta(data) {  // data contains loader() data
        return {
            title: "All Notes",
            description: "..."
        }
    }


-------
# Section 3: Routing & Layouts - Deep Dive

Layout Routes:
    - /routes/expenses.jsx : 
        - called as Layout routes. because folder is also present for 'expenses'.
        - Here, we have to pass layout detail (routing details).
        - return (
            <main>
                <p>Expenses Layout</p>
                <Outlet />  // render /expenses folder files
            </main>
        )
    - /routes/expenses/$id.js
      /routes/expenses/add.js
      /routes/expenses/analysis.js
    - /routes/expenses.raw: /expenses/raw
        - not part of expenses layout file. So CSS will not get shared

Pathless Layout Routes:
    - used to share CSS with non child components.
    - It wont add extra path. (/expenses)
    - create folder start with "__" and create jsx file with same name
        - /routes/__app/expenses/$id.js
          /routes/__app/expenses/add.js
          /routes/__app/expenses/analysis.js
          /routes/__app/expenses.raw: /expenses/raw
        - /routes/__app.jsx:
            - register css to this routing file, it will get shared to all components.
            - add Links()
            - return <Outlet />

Resourse Route:
    - when you want to return just data. Add only loader() method and return the data.
Splat Route:
    - $.jsx
    - empty file name, which matches all routes which are not present.

URL Search pattern:
    - const [searchParams, setSearchParams] = useSearchParams();
        // searchParams: contains current query params values
        // setSearchParams: used to set query params value
    const authMode = searchParams.get('mode');  // ?mode=login  // mode=signup

server file:
    - when you want to execute code only in server side, then name the file ends with "server.jsx". 
        So that, it will not render in client side
    - database.server.jsx

server side validation:
    -   <form method="post" onSubmit={submitHandler}>
        function submitHandler(event) {
            event.preventDefault();
            // perform validations...
            submit(event.target, { 
                action: 'expenses/add',
                method: 'post'
            });   // hook
        }
    - This method also have 'useSubmit' hook.
        - import { useSubmit } from '@remix-run/react';
        const submit = useSubmit()
        submit()    // works same as submit button clicked in form.

useMatches() hook:
    This hook contains entire application routing related data.
    - Used to share data during, when routing from one page to another
useParams() hook:
    - Used to get current Params value

useFetcher() hook:
    - When we use loader(), action() or submit() method, once execution completed, this functions will do navigation.
    - When we want to write a code, that dont want to navigate, then use useFetcher() hook.
    - const fetcher = useFetcher() 
        fetcher.submit(null, {method: 'delete', action: `/expenses/${id}`})\
    - In above example, once delete request completed, it wont trigger loader() method again for GET call.
    - fetcher also contain the detail of useNavigate() hook.
        fetcher.state !== 'idle'

bcryptjs library :
    - hash() : encrypt the password
    - compare(): compare the password with encrypted password

createCookieSessionStorage: 
    - used to store session cookies.
    - import {createCookieSessionStorage} from '@re'


```