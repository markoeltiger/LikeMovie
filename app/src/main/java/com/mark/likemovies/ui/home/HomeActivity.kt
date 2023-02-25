package com.mark.likemovies.ui.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.navigation.NavigationView
import com.mark.likemovies.ui.filter.FilterActivity
import com.mark.likemovies.R
import com.mark.likemovies.Subscribe
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import com.mark.likemovies.likedmovies
import com.mark.likemovies.ui.auth.LoginActivity
import com.mark.likemovies.ui.auth.SignupActivity
import com.mark.likemovies.ui.details.DetailsActivity
import com.mark.likemovies.util.Constants
import com.mark.moviesexpert.ui.movie.MoviePagingAdapter
import com.shashank.sony.fancytoastlib.FancyToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), MoviePagingAdapter.OnItemClicked {
    lateinit var homeRecyclerView: RecyclerView
    lateinit var drawerLayout: DrawerLayout
    var navigationView: NavigationView? = null

    lateinit var toolbar: Toolbar
    val movieAdapter = MoviePagingAdapter()
    var layoutManager: LinearLayoutManager? = null

    var user_id = 0
    var user_name = "Guest"
    var user_email = "guest@gooodbad.com"
    val viewModel: HomeViewModel by viewModels()
    lateinit var sharedpreferences: SharedPreferences
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.ideamenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.idea) {
            if (isloggedin()) {
                val idea = Intent(
                    this,
                    FilterActivity::class.java
                )
                startActivity(idea)
                return true
            } else {
                FancyToast.makeText(
                    this,
                    "You Must Login To Make an action",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    false
                ).show()
                val gotologin = Intent(
                    this,
                    LoginActivity::class.java
                )
                startActivity(gotologin)
            }
        }
        if (id == R.id.filter) {
            if (isloggedin()) {
                FancyToast.makeText(
                    this,
                    "Added To Loved Movies",
                    FancyToast.LENGTH_LONG,
                    FancyToast.SUCCESS,
                    false
                ).show()
                item.setIcon(R.drawable.ic_baseline_favorite_24)
                return true
            } else {
                FancyToast.makeText(
                    this,
                    "You Must Login To Make an action",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    false
                ).show()
                val gotologin = Intent(
                    this,
                    LoginActivity::class.java
                )
                startActivity(gotologin)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        sharedpreferences = getSharedPreferences(
            Constants.USER_DETAILS_SharedPrefs,
            android.content.Context.MODE_PRIVATE
        )

        initViews()
        setupMainRv()
        setupObservers()
        setupNavigationDrawer()
    }


    fun setupObservers() {
        viewModel.list.observe(this, Observer {

            movieAdapter.submitData(lifecycle, it)
        })
    }

    fun setupMainRv() {
        layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val snapHelper: SnapHelper = PagerSnapHelper()
        homeRecyclerView.setLayoutManager(layoutManager)
        snapHelper.attachToRecyclerView(homeRecyclerView)
        homeRecyclerView.adapter = movieAdapter

    }

    fun initViews() {
        homeRecyclerView = findViewById(R.id.recyclerview)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        toolbar = findViewById<View>(R.id.toolbar2) as Toolbar
        navigationView = findViewById(R.id.main_nav_view)

        setSupportActionBar(toolbar)
        movieAdapter.setOnItemClickListener(object : MoviePagingAdapter.OnItemClicked {

            override fun onItemClick(position: Int, actionType: String, item: SingleMovie?) {
                if (isloggedin()) {
                    if (actionType == "details") {
                        val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
                        intent.putExtra("data", item)
                        startActivity(intent)
                    } else if (actionType == "like") {
                        lifecycleScope.launch(Dispatchers.Main) {
                            try {
                                var likeReactResponse =
                                    viewModel.reactToEntertainment(0, "like", user_id, item!!.id)
                                if (likeReactResponse.isSuccessful) {
                                    FancyToast.makeText(
                                        this@HomeActivity,
                                        "تم الإعجاب بالفيلم بنجاح",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        false
                                    ).show()
                                    layoutManager?.scrollToPosition(position + 1)
                                }
                            } catch (e: Exception) {
                                FancyToast.makeText(
                                    this@HomeActivity,
                                    "حدث خطأ أثناء الإعجاب بالفيلم برجاء المحاولة مرة اخري",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                            }

                        }
                    } else if (actionType == "dislike") {
                        lifecycleScope.launch(Dispatchers.Main) {
                            try {
                                var likeReactResponse =
                                    viewModel.reactToEntertainment(0, "like", user_id, item!!.id)
                                if (likeReactResponse.isSuccessful) {
                                    FancyToast.makeText(
                                        this@HomeActivity,
                                        "تم الغاء الإعجاب بالفيلم بنجاح",
                                        FancyToast.LENGTH_LONG,
                                        FancyToast.CONFUSING,
                                        false
                                    ).show()
                                    layoutManager?.scrollToPosition(position + 1)

                                }
                            } catch (e: Exception) {
                                FancyToast.makeText(
                                    this@HomeActivity,
                                    "حدث خطأ أثناء إلغاء الإعجاب بالفيلم برجاء المحاولة مرة اخري",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    false
                                ).show()

                            }

                        }
                    }


                } else {
                    FancyToast.makeText(
                        this@HomeActivity,
                        "You Must Login To Make an action",
                        FancyToast.LENGTH_LONG,
                        FancyToast.ERROR,
                        false
                    ).show()
                    val signinintent = Intent(
                        this@HomeActivity,
                        LoginActivity::class.java
                    )
                    startActivity(signinintent)
                }
            }
        })


    }

    fun setupNavigationDrawer() {
        if (!isloggedin()) {
            navigationView?.inflateMenu(R.menu.notloggedin)
            navigationView?.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
                val id = item.itemId
                print(id)
                if (id == R.id.Nav_Logins) {
                    val loginintent = Intent(
                        this,
                        LoginActivity::class.java
                    )
                    startActivity(loginintent)
                }
                if (id == R.id.Nav_signups) {
                    val signupintent = Intent(
                        this,
                        SignupActivity::class.java
                    )
                    startActivity(signupintent)
                }
                false
            })
        } else {
            navigationView?.inflateMenu(R.menu.mainmenu)
            navigationView?.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener { item ->
                val id = item.itemId
                if (id == R.id.Nav_home) {

                    if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        drawerLayout.closeDrawer(Gravity.RIGHT)
                    } else {
                        drawerLayout.openDrawer(Gravity.RIGHT)
                    }

                }
                if (id == R.id.Nav_likes) {
                    val likesintent = Intent(
                        this,
                        likedmovies::class.java
                    )
                    likesintent.putExtra("type", "liked")
                    startActivity(likesintent)
                }
                if (id == R.id.Nav_Premuim) {
                    val Subscribeintent = Intent(
                        this,
                        Subscribe::class.java
                    )
                    startActivity(Subscribeintent)
                }
                if (id == R.id.Log_out) {
                    val logoutIntent = Intent(
                        this,
                        LoginActivity::class.java
                    )
                    startActivity(logoutIntent)
                    sharedpreferences.edit().clear().apply()
                }
                if (id == R.id.Nav_waiting_list) {
                    val waitingintent = Intent(
                        this,
                        likedmovies::class.java
                    )
                    waitingintent.putExtra("type", "loved")
                    startActivity(waitingintent)
                }
                false
            })
        }
        toolbar.showOverflowMenu()

        ViewCompat.setLayoutDirection(toolbar, ViewCompat.LAYOUT_DIRECTION_RTL)

        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.openNavigation,
            R.string.closeNavigation
        ) {
            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                if (item != null && item.itemId == android.R.id.home) {
                    if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        drawerLayout.closeDrawer(Gravity.RIGHT)
                    } else {
                        drawerLayout.openDrawer(Gravity.RIGHT)
                    }
                }
                return false
            }
        }

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onItemClick(position: Int, fileId: String, item: SingleMovie?) {
        TODO("Not yet implemented")
    }

    fun isloggedin(): Boolean {
        var logged = false
        logged = sharedpreferences.getBoolean("logged", false)
        if (logged) {
            user_id = sharedpreferences.getString("id", "0")?.toInt()!!
            user_email = sharedpreferences.getString("email", "guest@gooodbad.com")!!
            user_name = sharedpreferences.getString("username", "Guest")!!

        }
        return logged
    }
}



