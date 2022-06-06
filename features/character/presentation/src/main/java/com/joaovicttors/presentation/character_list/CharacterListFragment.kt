package com.joaovicttors.presentation.character_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovicttors.commons.EndlessScrollListener
import com.joaovicttors.presentation.character_list.adapter.horizontal.CharacterHListAdapter
import com.joaovicttors.presentation.character_list.adapter.vertical.CharacterVListAdapter
import com.joaovicttors.presentation.databinding.FragmentCharacterListBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by inject()

    private lateinit var binding: FragmentCharacterListBinding
    private lateinit var verticalAdapter: CharacterVListAdapter
    private lateinit var horizontalAdapter: CharacterHListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, ATTACH_TO_ROOT)

        verticalAdapter = CharacterVListAdapter()
        horizontalAdapter = CharacterHListAdapter()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewStateObserver()
        setupRecyclerView()

        viewModel.getCharacterList()
    }

    private fun viewStateObserver() {
        lifecycleScope.launch { ->
            repeatOnLifecycle(Lifecycle.State.STARTED) { ->
                viewModel.viewState.collect { viewState ->
                    if (viewState.data.isNotEmpty()) {
                        if (viewModel.offset == 20) {
                            horizontalAdapter.submitList(viewState.data.subList(0, 5))
                            verticalAdapter.submitList(viewState.data.subList(5, viewState.data.size))
                        } else {
                            verticalAdapter.submitList(viewState.data)
                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.horizontalRecyclerView.adapter = horizontalAdapter
        binding.horizontalRecyclerView.layoutManager = LinearLayoutManager(context).apply { ->
            orientation = LinearLayoutManager.HORIZONTAL
        }

        binding.verticalRecyclerView.adapter = verticalAdapter
        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(context).apply { ->
            orientation = LinearLayoutManager.VERTICAL
            binding.verticalRecyclerView.addOnScrollListener(EndlessScrollListener(this) { viewModel.getCharacterList() })
        }
    }

    private companion object {
        private const val ATTACH_TO_ROOT: Boolean = false
    }
}