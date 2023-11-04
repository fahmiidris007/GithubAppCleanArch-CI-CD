package com.fahmiproduction.githubappcleanarch.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.fahmiproduction.githubappcleanarch.R
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.domain.model.User
import com.fahmiproduction.githubappcleanarch.databinding.ActivityDetailUserBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var isFavorite = false
    private lateinit var userData: User
    private val detailViewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_DATA)
        showDetailUser(username)

    }

    private fun showDetailUser(username: String?) {
        username?.let {
            detailViewModel.getDetailUser(username).observe(this) { user ->
//                if (user != null) {
                when (user) {
                    is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.apply {
                            progressbar.visibility = View.GONE
                            tvName.text = user.data?.name
                            tvUsername.text = user.data?.login
                            tvCompany.text = user.data?.company
                            tvLocation.text = user.data?.location
                            numOfRepo.text = user.data?.publicRepos.toString()
                            tvFollowers.text = user.data?.followers.toString()
                            tvFollowings.text = user.data?.following.toString()
                        }

                        Glide.with(this@DetailUserActivity)
                            .load(user.data?.avatarUrl)
                            .into(binding.ivAvatarImg)


                        userData = user.data!!
                        detailViewModel.getFavoriteState(username)
                            ?.observe(this) { userData ->
                                isFavorite = userData.isFavorite == true
                                setStatusFavorite(isFavorite)
                            }
                    }

                    is Resource.Error -> {
                        binding.progressbar.visibility = View.GONE
                        binding.noData.visibility = View.VISIBLE
                    }
                }
                setStatusFavorite(isFavorite)
                binding.fabFavorite.setOnClickListener {
                    if (isFavorite) {
                        userData.isFavorite = !isFavorite
                        detailViewModel.deleteFavoriteUser(user.data!!)
                        isFavorite = !isFavorite
                    } else {
                        userData.isFavorite = !isFavorite
                        detailViewModel.insertFavoriteUser(user.data!!)
                        isFavorite = !isFavorite
                    }
                    setStatusFavorite(isFavorite)
                }
//                }

            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_24
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.baseline_favorite_border_24
                )
            )
        }
    }
}