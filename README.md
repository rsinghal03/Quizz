# Quizz

Part1 demo: Show selection of option moving back and forth while atttempting quiz

<a href="https://imgflip.com/gif/3f57vl"><img src="https://i.imgflip.com/3f57vl.gif" title="made at imgflip.com"/></a>

Part2 demo: Shows scrolling feature

<a href="https://imgflip.com/gif/3f585i"><img src="https://i.imgflip.com/3f585i.gif" title="made at imgflip.com"/></a>

Part3 demo: Shows review of the attempted quiz

<a href="https://imgflip.com/gif/3f587i"><img src="https://i.imgflip.com/3f587i.gif" title="made at imgflip.com"/></a>

# About the project

The project has been developed using the MVP architecture pattern. 

Libraries used are:
1. Dagger 2
2. RxJava
3. RoomDatabase
4. Stetho
5. Retrofit
6. Gson

Dagger is used for dependency injection

The retorfit is used to make the api call while intercepting the request using okhttp interceptor and getting the mock data.

RxJava is used to manage the network call on background thread and getting the result on main thread.

Roomdata base is used to save the user attempt and populate when requested for review. For custom data type, type converter is used. 
Stetho is use to manage the local database

# App flow

When user launch the app. 
The app shows start quiz button in ListOfQuizzFragment. This fragment future scope is to show list of quiz with 
each item showing whether quiz has been attemted or not.

Clicking on start quiz launch QuizzAttemptFragment where quiz question description and dynamically added radio button view with text is
shown. This page also shows button to move to next and previous question.
When you reach to last question the tick button is shown which means submit the quiz.

After submit the user is navigated to QuizReusltDetailsFragment where user is shown with mark she scored. Review button is also gieven to check the question. Correct question are shown with green text color and red for wrong question
