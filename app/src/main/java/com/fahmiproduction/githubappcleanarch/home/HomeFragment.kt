package com.fahmiproduction.githubappcleanarch.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmiproduction.githubappcleanarch.core.data.Resource
import com.fahmiproduction.githubappcleanarch.core.ui.ListUserAdapter
import com.fahmiproduction.githubappcleanarch.databinding.FragmentHomeBinding
import com.fahmiproduction.githubappcleanarch.detail.DetailUserActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val userAdapter = ListUserAdapter()
            userAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DATA, selectedData.login)
                }
                startActivity(intent)
            }

            homeViewModel.user.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    when (user) {
                        is Resource.Loading -> binding.progressbar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressbar.visibility = View.GONE
                            binding.noData.visibility = View.GONE
                            userAdapter.setData(user.data)
                        }

                        is Resource.Error -> {
                            binding.progressbar.visibility = View.GONE
                            binding.noData.visibility = View.VISIBLE
                        }
                    }
                }
            }

            with(binding.rvUser) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
