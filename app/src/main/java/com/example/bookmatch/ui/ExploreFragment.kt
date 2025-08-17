package com.example.bookmatch.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.bookmatch.R
import com.example.bookmatch.data.Books
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.Book
import com.google.android.material.textview.MaterialTextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var bookCard: CardView
@SuppressLint("StaticFieldLeak")
private lateinit var bookLoading: ProgressBar
private lateinit var bookName: MaterialTextView
private lateinit var bookSynopsis: MaterialTextView

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment(private val userEmail: String) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore, container, false)

        bookCard = view.findViewById(R.id.book_card)
        bookLoading = view.findViewById(R.id.book_loading)
        bookName = view.findViewById(R.id.book_name)
        bookSynopsis = view.findViewById(R.id.book_synopsis)
        var isDialogShown = false
        var animator: ViewPropertyAnimator? = null
        val cardStartX = bookCard.x
        val cardStartY = bookCard.y
        val bookCardLayoutParams = bookCard.layoutParams as ViewGroup.MarginLayoutParams
        val bookCardMarginLeft = bookCardLayoutParams.leftMargin.toFloat()
        val bookCardMarginTop = bookCardLayoutParams.topMargin.toFloat()

        loadBookData()

        bookCard.setOnTouchListener(View.OnTouchListener { view, event ->
            val displayMetrics = resources.displayMetrics
            val cardWidth = bookCard.width
            val cardHeight = bookCard.height

            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX
                    val newY = event.rawY
                    animator?.cancel()
                    bookCard.x = newX - (cardWidth / 2)
                    bookCard.y = newY - (cardHeight / 2)
                    isDialogShown = false
                }

                MotionEvent.ACTION_UP -> {
                    val currentX = bookCard.x
                    val currentY = bookCard.y

                    animator?.cancel()
                    animator = bookCard.animate()
                        .x(cardStartX + bookCardMarginLeft)
                        .y(cardStartY + bookCardMarginTop)
                        .setDuration(150)
                    animator.withEndAction {
                        if (currentY + (cardHeight / 2) < displayMetrics.heightPixels.toFloat() * 0.25 && !isDialogShown) {
                            isDialogShown = true
                            val signUpDialog = AddReviewDialog(requireContext(), R.style.DialogTheme,
                                userEmail, bookName.text.toString(), this)
                            signUpDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                            signUpDialog.show()
                        }

                        if (currentX + (cardWidth / 2) < displayMetrics.widthPixels.toFloat() * 0.25 && !isDialogShown) {
                            loadBookData()
                        }

                        if (currentX + (cardWidth / 2) > displayMetrics.widthPixels.toFloat() * 0.75 && !isDialogShown) {
                            Users.getUser(userEmail).addItemMyList(bookName.text.toString())
                            loadBookData()
                            Toast.makeText(context, R.string.book_added, Toast.LENGTH_SHORT).show()
                        }
                    }
                    animator.start()
                }
            }

            // required to by-pass lint warning
            view.performClick()
            return@OnTouchListener true
        })

        return view
    }

    fun loadBookData() {
        bookName.visibility = View.GONE
        bookSynopsis.visibility = View.GONE
        bookLoading.visibility = View.VISIBLE

        val book: Book = Books.getRandomBook()

        bookName.text = book.getName()
        bookSynopsis.text = book.getSynopsis()
        bookLoading.visibility = View.GONE
        bookName.visibility = View.VISIBLE
        bookSynopsis.visibility = View.VISIBLE
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(userEmail: String, param1: String, param2: String) =
            ExploreFragment(userEmail).apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}