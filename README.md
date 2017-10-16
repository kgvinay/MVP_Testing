# MVP_Testing
A Simple Example describing the usage of MVP.

Including: 

 - Clean Architecture
 - Use Cases
 - Localization
 - (SwipeRefreshLayout, ProgressBar,...)
    
Project : Fetch different information(Starred Repositories, Users, User Repositories) from Github and present them via Recycler View

Libraries Used :

1. ButterKnife
2. Dagger
3. RxJava
4. Retrofit

App Flow :

1. Model :
    - Entity models to capture the Github API data
    - Simple POJOs
    - http://www.jsonschema2pojo.org/
    
2. View :
     - Interface for the Activity - Presenter Communication
     - Marker Interface
    
3. Presenter
   - Handles logic to be presented in the App.
    
      Use Cases :
         - Business Logic is composed via Use Cases.
         - Easily testable and decoupled.
         
     Repository :
         - API Requests to fetch the data.
         
  
   
