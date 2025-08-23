package com.example.bookmatch.ui

import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.bookmatch.R
import com.example.bookmatch.adapter.ReviewsAdapter
import com.example.bookmatch.data.Users
import com.example.bookmatch.entity.Review

class DeleteReviewDialog(context: Context,
                         themeResId: Int,
                         singleItem: Boolean,
                         userEmail: String,
                         reviewListItem: Review,
                         reviewsAdapter: ReviewsAdapter,
                         emptyText: TextView): Dialog(context, themeResId) {

    private var confirmDeleteReview: Button
    private var denyDeleteReview: Button

    init {
        setContentView(R.layout.dialog_delete_review)
        confirmDeleteReview = findViewById(R.id.confirm_delete_review)
        denyDeleteReview = findViewById(R.id.deny_delete_review)

        confirmDeleteReview.setOnClickListener {
            Users.getUser(userEmail).removeItemReviewList(reviewListItem)
            reviewsAdapter.reviewsList!!.remove(reviewListItem)
            reviewsAdapter.notifyDataSetChanged()
            if (singleItem) {
                emptyText.visibility = View.VISIBLE
            } else {
                emptyText.visibility = View.GONE
            }
            dismiss()
            Toast.makeText(context, R.string.review_removed, Toast.LENGTH_SHORT).show()
        }

        denyDeleteReview.setOnClickListener {
            dismiss()
        }
    }

}