package com.example.multilanguage

open class SingletonHolder<out T: Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}
// when not use argument using by lazy instead
//interface GitHubService {
//
//    companion object {
//        val instance: GitHubService by lazy {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .build()
//            retrofit.create(GitHubService::class.java)
//        }
//    }
//}