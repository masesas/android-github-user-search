package com.assesment.githubuser.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.assesment.domain.model.GithubUser
import com.assesment.githubuser.databinding.ItemUserListBinding
import com.assesment.githubuser.utils.loadingImgPlaceHolder
import com.assesment.shared.ui.BaseRecyclerViewAdapter
import com.assesment.shared.ui.BaseViewHolder
import com.bumptech.glide.Glide


class SearchResultAdapter(
    private val onClick: ((GithubUser?) -> Unit)? = null
) :
    BaseRecyclerViewAdapter<GithubUser, ItemUserListBinding, SearchResultAdapter.SearchResultViewHolder>(
        SearchResultDiffUtil()
    ) {

    class SearchResultViewHolder(
        private val binding: ItemUserListBinding,
        private val onClick: ((GithubUser?) -> Unit)? = null
    ) : BaseViewHolder<GithubUser, ItemUserListBinding>(binding) {

        init {
            binding.root.setOnClickListener {
                onClick?.invoke(getRowItem())
            }
        }

        override fun bind() {
            getRowItem()?.let {
                binding.tvName.text = it.username

                Glide.with(itemView.context)
                    .load(it.avatarUrl)
                    .placeholder(loadingImgPlaceHolder(itemView.context))
                    .into(binding.imgAvatar)
            }
        }
    }

    class SearchResultDiffUtil : DiffUtil.ItemCallback<GithubUser>() {
        override fun areItemsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GithubUser, newItem: GithubUser): Boolean =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemUserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )

        return SearchResultViewHolder(binding, onClick = onClick)
    }
}