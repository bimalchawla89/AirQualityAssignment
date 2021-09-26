package com.airquality.viewbinding


import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * A ViewHolder that contains the reference to the [ViewBinding] generated from your layout file.
 * This can be used in kotlin with a typealias to make the code a bit cleaner.
 *
 * @author Simon.Sickle@pilottravelcenters.com
 * @param T [ViewBinding] class that is generated for your ViewHolder's view
 * @property binding the instantiated [ViewBinding] class for your ViewHolder's view
 * @see [RecyclerView.ViewHolder]
 */
class BindingViewHolder<T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)