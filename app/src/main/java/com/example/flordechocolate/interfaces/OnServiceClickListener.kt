package com.example.flordechocolate.interfaces

import com.example.flordechocolate.data.models.ServiceItemModel

interface OnServiceClickListener{
    fun onClick(item: ServiceItemModel)
}