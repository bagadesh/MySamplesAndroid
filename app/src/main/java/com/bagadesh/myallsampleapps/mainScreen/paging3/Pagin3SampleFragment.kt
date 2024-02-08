package com.bagadesh.myallsampleapps.mainScreen.paging3

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bagadesh.myallsampleapps.R
import com.bagadesh.myallsampleapps.databinding.FragmentPagin3SampleBinding
import kotlinx.coroutines.flow.collectLatest

class Pagin3SampleFragment : Fragment() {

    companion object {
        fun newInstance() = Pagin3SampleFragment()
    }

    private lateinit var viewModel: Pagin3SampleViewModel

    private lateinit var bindings: FragmentPagin3SampleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindings = FragmentPagin3SampleBinding.inflate(inflater, container, false)
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(Pagin3SampleViewModel::class.java)
        val adapter = RecyclerViewAdapter()
        bindings.swipeToRefresh.setOnRefreshListener {
            //viewModel.refressh()
            //adapter.refresh()
            viewModel.refressh2()
        }
        bindings.recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CollectionLoadStateAdapter({}),
            footer = CollectionLoadStateAdapter({})
        )
        with(viewModel) {
            lifecycleScope.launchWhenCreated {
                pagingDat2.collectLatest {
                    adapter.submitData(it)
                }
            }
            lifecycleScope.launchWhenCreated {
                loading.collectLatest {
                    bindings.swipeToRefresh.isRefreshing = it
                }
            }
        }
        println("bitwise ${(1 and 2)}")
    }
}





data class UIData(
    val view: ViewModel
)

interface ViewModelDiff {
    fun getContentId(): String
}

class CustomViewModel(val id: String) : ViewModel(), ViewModelDiff {
    override fun getContentId(): String {
        return id
    }
}

class CustomRecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    val composeView = ComposeView(view.context)

    init {
        val container = view.findViewById<ViewGroup>(R.id.container)
        container.addView(composeView)
    }

    fun bind(viewModel: ViewModel) {
        composeView.setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp)
                        .padding(10.dp)
                        .border(1.dp, color = Color.White, RoundedCornerShape(10.dp))
                    , contentAlignment = Alignment.Center
                ) {
                    Text(text = "cotentId = ${(viewModel as CustomViewModel)?.getContentId()}", color = Color.White)
                }
            }
        }
    }
}

val diff = object : DiffUtil.ItemCallback<UIData>() {
    override fun areItemsTheSame(oldItem: UIData, newItem: UIData): Boolean {
        return oldItem.view is ViewModelDiff && newItem.view is ViewModelDiff && oldItem.view.getContentId() == newItem.view.getContentId()
    }

    override fun areContentsTheSame(oldItem: UIData, newItem: UIData): Boolean {
        return oldItem.view is ViewModelDiff && newItem.view is ViewModelDiff && oldItem.view.getContentId() == newItem.view.getContentId()
    }
}

class RecyclerViewAdapter : PagingDataAdapter<UIData, CustomRecyclerViewHolder>(diff) {
    override fun onBindViewHolder(holder: CustomRecyclerViewHolder, position: Int) {
        getItem(position)?.view?.let {

            holder.bind(viewModel = it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomRecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return CustomRecyclerViewHolder(view)
    }

}

class CollectionLoadStateAdapter constructor(
    private val refryCallback: () -> Unit,
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder(context = parent.context, refryCallback = refryCallback)
    }

    override fun onViewRecycled(holder: LoadStateViewHolder) {
        // This is from the previous guidance
        // NOTE: You **do not** want to do this with Compose 1.2.0-beta02+
        // and RecyclerView 1.3.0-alpha02+
        holder.composeView.disposeComposition()
    }
}

class LoadStateViewHolder constructor(
    context: Context,
    val composeView: ComposeView = ComposeView(context),
    private val refryCallback: () -> Unit,
) : RecyclerView.ViewHolder(composeView) {

    init {
        // This is from the previous guidance
        // NOTE: **Do not** do this with Compose 1.2.0-beta02+
        // and RecyclerView 1.3.0-alpha02+
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )
    }

    fun bind(loadState: LoadState) {

        composeView.setContent {
            MaterialTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp), contentAlignment = Alignment.Center
                ) {
                    if (loadState is LoadState.Loading) {
                        CircularProgressIndicator()
                    } else if (loadState is LoadState.Error) {
                        Button(onClick = { refryCallback() }) {
                            Text(text = "Retry")
                        }
                    }
                }
            }

        }
    }
}