package com.ivzb.irish_rail.util

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ivzb.irish_rail.R

fun Fragment.createSearchMenu(
    menu: Menu,
    menuInflater: MenuInflater,
    searchListener: SearchView.OnQueryTextListener
) {
    menuInflater.inflate(R.menu.search_menu, menu)

    requireActivity().apply {
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView: SearchView? = searchItem?.actionView as SearchView

        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(searchListener)
            setOnQueryTextFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    view.showKeyboard()
                } else {
                    view.dismissKeyboard()
                }
            }

            setQuery("", true)
            queryHint = getString(R.string.search)
            isIconifiedByDefault = false
            isIconified = false
            requestFocus()
        }
    }
}

fun Fragment.updateTitle(title: String, query: String? = null) {
    requireActivity().title = if (query?.isEmpty() ?: false) title else "$title ($query)"
}

fun Fragment.updateTitle(@StringRes title: Int, query: String? = null) {
    updateTitle(getString(title), query)
}

fun Fragment.closeSearch(searchItem: MenuItem?) {
    searchItem?.collapseActionView()
}

fun Fragment.clearSearch(searchItem: MenuItem?) {
    (searchItem?.actionView as? SearchView)?.setQuery("", true)
}

inline fun <reified VM : ViewModel> FragmentActivity.provideViewModel(
    viewModelFactory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, viewModelFactory).get(VM::class.java)

inline fun <reified VM : ViewModel> Fragment.provideViewModel(
    viewModelFactory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, viewModelFactory).get(VM::class.java)
