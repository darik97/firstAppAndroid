package mobiledev.firstapp

import android.app.Activity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import com.google.gson.Gson

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        Thread(Runnable {
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url("https://api.github.com/users/darik97/repos")
                    .build()
            val response = client.newCall(request).execute()
            val responseText = response.body()!!.string()
            val repos = Gson().fromJson(responseText, GitHubRepositoryInfo.List::class.java)
            runOnUiThread {
                val adapter = CustomAdapter(repos)
                recycler.adapter = adapter
            }
            android.util.Log.d("Repos", repos.joinToString { it.name })
        }).start()
    }

    data class GitHubRepositoryInfo(val name: String) {

        class List : ArrayList<GitHubRepositoryInfo>()
    }
}
