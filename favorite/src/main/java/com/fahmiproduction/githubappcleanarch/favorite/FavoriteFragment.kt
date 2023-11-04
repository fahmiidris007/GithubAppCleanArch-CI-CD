package com.fahmiproduction.githubappcleanarch.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmiproduction.githubappcleanarch.core.ui.ListUserAdapter
import com.fahmiproduction.githubappcleanarch.detail.DetailUserActivity
import com.fahmiproduction.githubappcleanarch.favorite.databinding.FragmentFavoriteBinding
import com.fahmiproduction.githubappcleanarch.favorite.di.favoriteViewModelModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        loadKoinModules(favoriteViewModelModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as AppCompatActivity
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        val userAdapter = ListUserAdapter()
        userAdapter.onItemClick = { selectedData ->
            val intent = Intent(activity, DetailUserActivity::class.java).apply {
                putExtra(DetailUserActivity.EXTRA_DATA, selectedData.login)
            }
            startActivity(intent)
        }

        favoriteViewModel.favoriteUser.observe(viewLifecycleOwner) { user ->

            user.let { userData ->
                if (!userData.isNullOrEmpty()) {
                    userAdapter.setData(userData)
                } else {
                    binding.noData.visibility = View.VISIBLE
                    binding.rvUser.visibility = View.INVISIBLE
                }
            }
        }

        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}