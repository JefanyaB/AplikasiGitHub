package com.dicoding.picodiploma.aplikasigithubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.aplikasigithubuser.databinding.FragmentFollowBinding


class FollowerFragment : Fragment(R.layout.fragment_follow) {
    private lateinit var binding : FragmentFollowBinding
    private lateinit var viewModel: FollowerViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val login = activity?.intent?.extras?.getString(DetailUser.EXTRA_USERNAME)
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(FollowerViewModel::class.java)
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(requireActivity(),layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)

        recyclerView = binding.rvFollow
        login?.let { viewModel.setListFollower(it) }

        viewModel.listFollower.observe(viewLifecycleOwner){
            recyclerView.adapter = UserAdapter(it)
        }
        viewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it)
        }

    }
    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbBar.visibility = View.VISIBLE
        } else {
            binding.pbBar.visibility = View.GONE
        }
    }
}