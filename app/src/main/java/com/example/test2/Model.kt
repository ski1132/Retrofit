package com.example.test2

import com.google.gson.annotations.Expose

class User {
    @Expose
    var firstName: String? = null
        internal set
    @Expose
    var lastName: String? = null
        internal set
    @Expose
    var age: String? = null
        internal set
    @Expose
    var picture: String? = null
        internal set
}