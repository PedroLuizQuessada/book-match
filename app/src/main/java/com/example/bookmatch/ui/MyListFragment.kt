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
import com.example.bookmatch.adapter.MyListAdapter
import com.example.bookmatch.data.Users
import com.example.bookmatch.databinding.FragmentMyListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyListFragment(private val userEmail: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentMyListBinding
    private lateinit var myListAdapter: MyListAdapter
    private lateinit var emptyText: TextView
    private lateinit var myListView: ListView
    private lateinit var sortButton: ImageButton
    private lateinit var bookNameFilter: EditText
    private var sort: Boolean = true
    private var page = 0
    private val size = 10
    private var nextPageIsEmpty: Boolean = false
    private var myListUnfiltered: MutableList<String?> = mutableListOf()

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
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        val view = binding.root
        emptyText = binding.myListEmptyText
        myListView = binding.listview
        sortButton = view.findViewById(R.id.my_list_sort_button)
        bookNameFilter = view.findViewById(R.id.my_list_book_name_filter)
        myListAdapter = MyListAdapter(context, ArrayList(), userEmail)
        myListView.adapter = myListAdapter
        page = 0
        sort = true

        myListAdapter.myList!!.clear()
        myListUnfiltered.clear()
        bookNameFilter.setText("")
        loadMyList()

        sortButton.setOnClickListener{
            page = 0
            myListAdapter.myList!!.clear()
            myListUnfiltered.clear()
            bookNameFilter.setText("")
            sort = !sort
            loadMyList()
            if (sort) {
                sortButton.setImageResource(R.drawable.outline_arrow_downward_alt_24)
            }
            else {
                sortButton.setImageResource(R.drawable.outline_arrow_upward_alt_24)
            }

        }

        myListView.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

            }

            override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
                if (!nextPageIsEmpty && firstVisibleItem + visibleItemCount == totalItemCount && myListAdapter.myList!!.size >= size) {
                    bookNameFilter.setText("")
                    page++
                    loadMyList()
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
                myListAdapter.myList!!.clear()
                for (i in myListUnfiltered.filter { it!!.contains(filter, true) }.toCollection(ArrayList())) {
                    myListAdapter.myList!!.add(i)
                }
                myListAdapter.notifyDataSetChanged()
            }
        })

        return view
    }

    private fun loadMyList() {
        val myListData: MutableList<String> = Users.getUser(userEmail).getMyList(page * size, size, sort)
        for (i in myListData) {
            myListAdapter.myList!!.add(i)
            myListUnfiltered.add(i)
        }
        if (myListAdapter.myList!!.isNotEmpty()) {
            emptyText.visibility = View.GONE
        } else {
            emptyText.visibility = View.VISIBLE
        }
        nextPageIsEmpty = myListData.size < size
        myListAdapter.notifyDataSetChanged()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(userEmail: String, param1: String, param2: String) =
            MyListFragment(userEmail).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}