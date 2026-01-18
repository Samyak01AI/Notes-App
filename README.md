#📝 Firebase Note-Taking App (Android - Java)
Overview

This is a simple note-taking app built for Android using Java. It leverages Firebase Authentication for secure user login and signup, and Cloud Firestore to store and manage notes in real-time. Each user has a private account, and all notes are saved under their unique user ID, ensuring data privacy.

Features

User Authentication:

Sign up with email and password.

Log in and log out securely.

Password reset via email if forgotten.

Notes Management:

Add new notes with a title and description.

View all saved notes in a RecyclerView.

Edit existing notes.

Delete notes when no longer needed.

Real-time updates using Firebase Firestore.

Only display notes belonging to the logged-in user using getUid().

UI & UX:

Clean and organized CardView layout for each note.

Easy navigation to edit or delete notes.

Instant reflection of changes on the main screen.

Demo Video
(https://drive.google.com/file/d/1CR6pwCZkBqTaVJNa6ygoRWUUKKxG6dAQ/view?usp=classroom_web&authuser=1)

Tech Stack

Language: Java

Platform: Android Studio

Backend: Firebase Authentication, Firebase Firestore

UI Components: RecyclerView, CardView, ConstraintLayout, EditText, Buttons

How It Works

Authentication:

Users can register with an email and password.

Users can log in and access only their notes.

Forgot Password option allows resetting passwords via email.

Notes Storage:

Each note is stored in Firestore under the user's unique ID.

Notes are displayed in a RecyclerView for smooth scrolling.

Editing or deleting a note updates Firestore in real-time, ensuring instant updates.
