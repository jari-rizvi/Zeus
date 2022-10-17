package com.teamx.zeus.ui.fragments.notification

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.teamx.zues.dataclasses.notification.Doc
import com.teamx.zeus.R
import com.teamx.zeus.databinding.ItemNotificationBinding
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

class NotificationAdapter(
    private val context: Context,
    private val notificationArrayList: ArrayList<NotificationFragment.NotificationTableClass>?,
    private val onNotificationListener: OnNotificationListener
) : RecyclerView.Adapter<NotificationAdapter.NoticationViewHolder>() {
    private val wordCollectionList: List<String>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticationViewHolder {
        return NoticationViewHolder(
            ItemNotificationBinding.inflate(
                LayoutInflater.from(
                    context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NoticationViewHolder, position: Int) {
        val notification: Doc = notificationArrayList!![position].doc
        //        Picasso.get().load(AppConstants.imagePath(notification.getDisplayImage())).into(holder.itemNotificationBinding.notificationImage);

//        holder.itemNotificationBinding.notificationImage.setImageResource(notification.getNotificationImage());
        holder.itemNotificationBinding.notificationDescription.text =
            linkifyHashtags(notification.description)
        val stamp = Timestamp(System.currentTimeMillis())
        val date = Date(stamp.time)
        val f: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val f1: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val d = f.format(date)
        val d1 = f1.format(date)
        holder.itemNotificationBinding.notificationTime.text = d1
        val drawableImage = when (notification.notification_type) {
            "order" -> {
                R.drawable.icon_order_arrow
            }
            "profile" -> {
                R.drawable.icon_order_arrow
            }
            "verification" -> {
                R.drawable.bin
            }
            "coupon" -> {
                R.drawable.bin
            }
            "promotion" -> {
                R.drawable.icon_approved
            }
            "miscalleneous" -> {
                R.drawable.icon_order_arrow
            }
            else -> {
                R.drawable.icon_order_arrow
            }
        }

        Picasso.get().load(drawableImage).into(holder.itemNotificationBinding.notificationImage)
        holder.itemNotificationBinding.unreadCheck.isChecked = notificationArrayList[position].read

        val sb = SpannableStringBuilder(notification.description)
        val CUSTOM_TYPEFACE = ResourcesCompat.getFont(context, R.font.noah_bold)


    }

    override fun getItemCount(): Int {
        return notificationArrayList?.size ?: 0
    }

    inner class NoticationViewHolder(var itemNotificationBinding: ItemNotificationBinding) :
        RecyclerView.ViewHolder(
            itemNotificationBinding.root
        ) {
        init {
            itemView.setOnClickListener {
                onNotificationListener.OnNotificationClickListener(
                    adapterPosition
                )
            }
        }
    }

    fun linkifyHashtags(text: String): CharSequence {
        val linkifiedText = SpannableStringBuilder(text)
        val pattern = Pattern.compile("@\\w")
        val matcher = pattern.matcher(text)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            val hashtag = text.substring(start, end)
            val span = ForegroundColorSpan(Color.BLUE)
            linkifiedText.setSpan(span, 0, hashtag.length, 0)
        }
        return linkifiedText
    }
}