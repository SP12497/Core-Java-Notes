```ts
# app.module.ts:
<ul>
    <li [routerLinkActive]="'active'" [routerLinkActiveOptions]="{exact: true}">
        <a [routerLink]="">Home</a>
    </li>

    <li [routerLinkActive]="'active'">
        <a [routerLink]="['/about']">About</a>
    </li>
</ul>

<router-outlet></router-outlet>

--------
# app-routing.module.ts:
const routes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'home', component: HomeComponent},
    {path: 'about', component: AboutComponent},
    {path: 'courses', component: CoursesComponent},
    // {path: 'courses/:id', component: CourseComponent}, 
    {path: 'courses', children: [  
        {path: 'course/:id', component: CourseComponent}   // http://localhost:4200/courses/course/1
    ]},
    {path: '**', component: PageNotFoundComponent}
];


@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

--------
// courses.component.html:
<ul>
    <li *ngFor="let course of courses" [routerLink]="['/courses', course.id]">{{course.name}}</li>
</ul>
<button [routerLink]="['/courses', 1]" [queryParams]="{page: 1}">Course 1</button>  // http://localhost:4200/courses/1?page=1

--------
# about.component.ts:
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
export class AboutComponent implements OnInit {     // /about page
    constructor(private router: Router, private activatedRoute: ActivatedRoute) { }
    
    ngOnInit() {
        this.activatedRoute.snapshot.params.id
        this.activatedRoute.snapshot.paramMap.get('id')
        this.activatedRoute.snapshot.queryParams.id
        this.activatedRoute.snapshot.fragment
        this.activatedRoute.snapshot.paramMap.subscribe(params => {  // if the id is changed in the same component
            console.log(params.get('id'));
        });
        this.activatedRoute.queryParamMap.subscribe(params => {
            console.log(params.id);
        });
    }
    myNavigate() {
        this.router.navigate(['/contact']);       // http://localhost:4200/contact
        this.router.navigate("Home");           // http://localhost:4200/Home
        this.router.navigate(["Home"], {relativeTo: this.activatedRoute}); // http://localhost:4200/about/Home
        this.router.navigate(["Home"], {relativeTo: this.activatedRoute, queryParams: {id: 1}}); // http://localhost:4200/about/Home?id=1
        this.router.navigate(["Home"], {relativeTo: this.activatedRoute, fragment: "section1"}); // http://localhost:4200/about/Home#section1
        
        this.navigateByUrl("Home"); // http://localhost:4200/Home
        this.navigateByUrl("Home", {queryParams: {id: 1}}); // http://localhost:4200/Home?id=1
        this.navigateByUrl("Home", {fragment: "section1"}); // http://localhost:4200/Home#section1
        this.navigateByUrl("Home", {relativeTo: this.activatedRoute}); // http://localhost:4200/about/Home
    }
}


--------
# Route Guards:
const appRoute = [
    {
        path: "Courses", component: CoursesComponent,
        resolve: { courses: CourseResolveService },
        canDeactive: [CanDeactivateGuardService],
        canActive: [CourseGuardService]
    },
    {
        path: "Courses",
        canActivateChild: [CourseGuardService],
        children: [
            { path: "Course/:id", component: CourseComponent }
        ]
]


```