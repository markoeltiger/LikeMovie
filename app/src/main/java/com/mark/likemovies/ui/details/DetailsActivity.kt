package com.mark.likemovies.ui.details

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.mark.likemovies.R
import com.mark.likemovies.data.models.homeMovies.SingleMovie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    lateinit var MovieName: TextView
    lateinit var Movieyear: TextView
    lateinit var MovieCrew: TextView
    lateinit var MovieRate: TextView
    lateinit var MovieGenre: TextView
    lateinit var MovieStory: TextView
    lateinit var heros: TextView
    var mViewPager: ViewPager? = null
    var castRecyclerView: RecyclerView? = null
    lateinit var adapter: CastAdapter

    lateinit var story: TextView
    var goHome: FloatingActionButton? = null
    var mIndicator: View? = null
    private var indicatorWidth = 0

    var mTopToolbar: Toolbar? = null
    lateinit var currentData: SingleMovie
    var mTabs: TabLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        initViews()
        getAndSetData()
        setTaps()
    }


    fun initViews() {
        mViewPager = findViewById<ViewPager>(R.id.viewPager)

        mTopToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        mTabs = findViewById(R.id.tab)
        //     simpleRatingBar=findViewById(R.id.simpleRatingBar);
        MovieName = findViewById<TextView>(R.id.tvMovieName)
        Movieyear = findViewById<TextView>(R.id.tvMovieYear)
        MovieCrew = findViewById<TextView>(R.id.tvMovieCrew)
        MovieRate = findViewById<TextView>(R.id.movieRate)
        goHome = findViewById<FloatingActionButton>(R.id.goHome)
        MovieGenre = findViewById<TextView>(R.id.movieGenre)
        MovieStory = findViewById(R.id.MovieStory)
        heros = findViewById(R.id.heros)
        mIndicator = findViewById(R.id.indicator)
        castRecyclerView = findViewById(R.id.Casts)

        story = findViewById(R.id.story)
        setSupportActionBar(mTopToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

    }


    fun getAndSetData() {
        currentData = (intent.getSerializableExtra("data") as? SingleMovie)!!
        MovieName.text = currentData.title
        MovieName.setText(currentData.title)
        Movieyear.setText(currentData.releaseYear.toString())
        MovieCrew.setText(currentData.casts.toString())
        story.text = currentData.storyAr
        MovieGenre.text = currentData.genresAr
        MovieRate.setText("${currentData.rating}   / 10")
        this.setTitle(currentData.releaseYear.toString())
    }

    fun setTaps() {
        mTabs!!.addTab(mTabs!!.newTab().setText("الأبطال"), 0)
        mTabs!!.addTab(mTabs!!.newTab().setText("القصة"), 1, true)
        mTabs!!.background = resources.getDrawable(R.drawable.tabcustomselector)
        currentData.casts.let {
            castRecyclerView?.setLayoutManager(LinearLayoutManager(this))
            adapter = CastAdapter(this, it)
            castRecyclerView?.adapter = adapter

        }
        mTabs!!.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                tab.select()
                tab.view.background = resources.getDrawable(R.drawable.gradient_bg)
                if (tab.position == 0) {
                    castRecyclerView?.visibility = View.VISIBLE
                    MovieStory.visibility = View.GONE
                } else {
                    try {
                        MovieStory.visibility = View.VISIBLE
                        castRecyclerView?.visibility = View.GONE

                        MovieStory.setText(currentData.storyAr)
                    } catch (e: Exception) {
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                tab.view.background = resources.getDrawable(R.drawable.tab_bg)
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        mTabs!!.post {
            indicatorWidth = mTabs!!.width / 2

            //Assign new width
            val indicatorParams =
                mIndicator?.getLayoutParams() as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            mIndicator?.setLayoutParams(indicatorParams)
        }
        heros.setOnClickListener {
            heros.setTextColor(Color.parseColor("#EF476f"))
            story.setTextColor(Color.parseColor("#000000"))

            //   MovieStory.setText(currentData.casts.toString())
        }
        story.setOnClickListener {
            story.setTextColor(Color.parseColor("#EF476f"))
            heros.setTextColor(Color.parseColor("#000000"))
            MovieStory.visibility = View.VISIBLE

            MovieStory.setText(currentData.storyAr)
        }
    }
}



