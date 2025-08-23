package com.example.bookmatch.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import com.example.bookmatch.R
import com.example.bookmatch.adapter.ReviewsAdapter
import com.example.bookmatch.data.Users
import com.example.bookmatch.databinding.FragmentReviewsBinding
import com.example.bookmatch.entity.Review
import com.example.bookmatch.enums.ReviewsSort

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewsFragment(private val userEmail: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentReviewsBinding
    private lateinit var reviewsAdapter: ReviewsAdapter
    private lateinit var emptyText: TextView
    private lateinit var reviewsView: ListView
    private lateinit var sortButton: ImageButton
    private lateinit var bookNameFilter: EditText
    private var sort: ReviewsSort = ReviewsSort.NAME_ASCENDING
    private var page = 0
    private val size = 10
    private var nextPageIsEmpty: Boolean = false
    private var reviewsListUnfiltered: MutableList<Review?> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        val view = binding.root
        emptyText = binding.reviewsEmptyText
        reviewsView = binding.listview
        sortButton = view.findViewById(R.id.reviews_sort_button)
        bookNameFilter = view.findViewById(R.id.reviews_book_name_filter)
        reviewsAdapter = ReviewsAdapter(context, ArrayList(), userEmail, emptyText)
        reviewsView.adapter = reviewsAdapter
        page = 0
        sort = ReviewsSort.NAME_ASCENDING

        reviewsAdapter.reviewsList!!.clear()
        reviewsListUnfiltered.clear()
        bookNameFilter.setText("")
        loadReviewList()

        sortButton.setOnClickListener{
            page = 0
            reviewsAdapter.reviewsList!!.clear()
            reviewsListUnfiltered.clear()
            bookNameFilter.setText("")
            if (sort == ReviewsSort.NAME_ASCENDING) {
                sortButton.setImageResource(R.drawable.outline_arrow_upward_alt_24)
                sort = ReviewsSort.NAME_DESCENDING
            }
            else if (sort == ReviewsSort.NAME_DESCENDING) {
                sortButton.setImageResource(R.drawable.outline_keyboard_double_arrow_down_24)
                sort = ReviewsSort.RATING_ASCENDING
            }
            else if (sort == ReviewsSort.RATING_ASCENDING) {
                sortButton.setImageResource(R.drawable.outline_keyboard_double_arrow_up_24)
                sort = ReviewsSort.RATING_DESCENDING
            }
            else {
                sortButton.setImageResource(R.drawable.outline_arrow_downward_alt_24)
                sort = ReviewsSort.NAME_ASCENDING
            }
            loadReviewList()

        }

        reviewsView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (!nextPageIsEmpty && firstVisibleItem + visibleItemCount == totalItemCount && reviewsAdapter.reviewsList!!.size >= size) {
                    bookNameFilter.setText("")
                    page++
                    loadReviewList()
                }
            }
        })

        bookNameFilter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val filter = s.toString()
                reviewsAdapter.reviewsList!!.clear()
                for (i in reviewsListUnfiltered.filter { it?.getBookItem()?.getBookName()?.contains(filter, true) ?: false }.toCollection(ArrayList())) {
                    reviewsAdapter.reviewsList!!.add(i)
                }
                reviewsAdapter.notifyDataSetChanged()
            }
        })

        return view
    }

    private fun loadReviewList() {
        val reviewListData: MutableList<Review> = Users.getUser(userEmail).getReviewList(page * size, size, sort)
        for (i in reviewListData) {
            reviewsAdapter.reviewsList!!.add(i)
            reviewsListUnfiltered.add(i)
        }
        if (reviewsAdapter.reviewsList!!.isNotEmpty()) {
            emptyText.visibility = View.GONE
        } else {
            emptyText.visibility = View.VISIBLE
        }
        nextPageIsEmpty = reviewListData.size < size
        reviewsAdapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ReviewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(userEmail: String, param1: String, param2: String) =
            ReviewsFragment(userEmail).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}