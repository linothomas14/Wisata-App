package com.example.wisataapp.model

class ResponseServer {
    // This class is to build a frame for bring to a server, so the server can give is a data what we want

    var status_code : Int? = null
    var message: String? = null
    // in my API, thats a 3 varibel like you see in the code
    var data : ArrayList<Wisata>? = null //but in this var, the type is object , that we must make new class to handle it
}