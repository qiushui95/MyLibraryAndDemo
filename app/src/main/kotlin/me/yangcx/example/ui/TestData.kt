package me.yangcx.example.ui

import me.yangcx.recycler.sundries.IEntity

data class TestData(val id: Int, var data: Int) : IEntity {
    override fun copySelf(): IEntity {
        return copy()
    }

    override fun isItemSame(other: Any): Boolean {
        return other is TestData && other.id == id
    }

    override fun isContentSame(other: Any): Boolean {
        return other is TestData && other.data == data
    }

    override fun getChangeList(other: Any, changeList: MutableList<String>) {
        changeList.add("0")
    }

    override fun toString(): String {
        return "id=$id, data=$data"
    }
}