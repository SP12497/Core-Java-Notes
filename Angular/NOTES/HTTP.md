```ts
HTTP Module:
- HTTPClientModule
- HTTPClient
- HTTPClientRequest
- HTTPClientResponse
- HTTPClientError
- HTTPClientJsonpModule
- HTTPClientXsrfModule
- HTTPClientInterceptor
- HTTPClientXhrBackend
- HTTPClientJsonpCallbackContext

Step 1: Import HTTPClientModule
    - app.module.ts:
    import { HTTPClientModule } from '@angular/common/http';
    @NgModule({
        imports: [HTTPClientModule]
    })

Step 2: Inject HTTPClient
Step 3: Make a request
    - app.component.ts:
    import { HTTPClient } from '@angular/common/http';
    constructor(private http: HTTPClient) {}

    const baseUrl = 'https://jsonplaceholder.typicode.com/posts';

    this.http.get(baseUrl).subscribe((data) => {
        console.log(data);
    });

    this.http.post(baseUrl, {
        userId: 1
        name: 'Sagar',
    }, {
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .subscribe((data) => {
        console.log(data);
    }, (error) => {
        console.log(error);
    });

// this.http.delete()
// this.http.patch()
// this.http.head()
// this.http.options()

# pipe:
    - app.component.ts:
    import { map } from 'rxjs/operators';
    this.http.get(baseUrl)
    .pipe(
        map((data) => {
            return data;
        }),
        filter((data) => {
            return data.userId === 1;
        })
    )
    .subscribe((data) => {
        console.log(data);
    });


---
Headers:
    const headers = new HttpHeaders({
        'Content-Type': 'application/json
    });

    this.http.put(baseUrl, {
        userId: 1
        name: 'Sagar',
    }, { headers })
    .subscribe((data) => {
        console.log(data);
    });

# set method:
    - set method is immutable.
    const headers = new HttpHeaders()
        . set('Content-Type', 'application/json')
        . set('Authorization': 'Bearer 123');
# append method:
    - append method is mutable.
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    headers.append('Authorization', 'Bearer 123');

---
Query params:
    - In Browser Network tab, we can getting body in single line (not formatted). Use print=pretty query param to get formatted body.

    this.http.get(baseUrl + '?print=pretty').subscribe((data) => {
        console.log(data);
    });


    this.http.get(baseUrl, {
        params: new HttpParams().set('print', 'pretty')
    }).subscribe((data) => {
        console.log(data);
    });

    




```