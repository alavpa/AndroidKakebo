package com.alavpa.data

import com.alavpa.data.database.DatabaseSource
import com.alavpa.data.preferences.PreferencesDataSource
import com.alavpa.data.resources.ResourcesDataSource
import com.alavpa.domain.Repository
import com.alavpa.domain.entity.Category
import com.alavpa.domain.entity.Notification
import com.alavpa.domain.entity.Period
import com.alavpa.domain.entity.Transaction

class DataRepository(
    private val databaseSource: DatabaseSource,
    private val resourcesDataSource: ResourcesDataSource,
    private val preferencesDataSource: PreferencesDataSource
) : Repository {
    override fun enableFirstDayNotifications(isEnabled: Boolean) {
        preferencesDataSource.setFirstDayEnabled(isEnabled)
    }

    override fun enableAutoNotifications(isEnabled: Boolean) {
        preferencesDataSource.setTransactionsAutoEnabled(isEnabled)
    }

    override fun enableTargetNotifications(isEnabled: Boolean) {
        preferencesDataSource.setTargetAchievedEnabled(isEnabled)
    }

    override fun getNotificationsList(): List<Notification> {
        return resourcesDataSource.getNotificationsList()
            .mapIndexed { index, text ->
                val id = index + 1L
                val isEnabled = when (id) {
                    Notification.FIRST_DAY_ID -> preferencesDataSource.getFirstDayEnabled()
                    Notification.AUTO_ID -> preferencesDataSource.getTransactionsAutoEnabled()
                    Notification.TARGET_ID -> preferencesDataSource.getTargetAchievedEnabled()
                    else -> false
                }

                Notification(id, text, isEnabled)
            }
    }

    override suspend fun deleteTransaction(transaction: Transaction): Int {
        return databaseSource.deleteTransaction(transaction.toTable())
    }

    override suspend fun getTransaction(id: Long): Transaction {
        return databaseSource.getTransaction(id).let { transactionTable ->
            transactionTable.toEntity(
                getCategory(transactionTable.categoryId),
                getPeriod(transactionTable.periodId)
            )
        }
    }

    override suspend fun getTransactions(): List<Transaction> {
        return databaseSource.getAllTransactions().map {
            it.toEntity(getCategory(it.categoryId), getPeriod(it.periodId))
        }
    }

    override suspend fun deleteCategory(category: Category): Int {
        return databaseSource.deleteCategory(category.toTable())
    }

    override suspend fun getCategory(id: Long): Category {
        return databaseSource.getCategory(id).toEntity()
    }

    override suspend fun getPeriod(id: Long?): Period? {
        return id?.let {
            databaseSource.getPeriod(it)?.toEntity()
        }
    }

    override suspend fun insertCategory(category: Category): Long {
        return databaseSource.insertCategory(category.toTable())
    }

    override suspend fun insertTransaction(transaction: Transaction): Long {
        val table = transaction.toTable()
        transaction.period?.let {
            table.periodId = databaseSource.insertPeriod(it.toTable())
        }
        return databaseSource.insertTransaction(table)
    }

    override suspend fun getCategories(): List<Category> {
        val tables = databaseSource.getAllCategories()
        return tables.map { it.toEntity() }
    }

    override suspend fun getIcons(): List<Int> {
        return resourcesDataSource.getIcons()
    }

    override suspend fun getTransactionsFromDate(from: Long): List<Transaction> {
        return databaseSource.getTransactionsByDate(from).map {
            it.toEntity(getCategory(it.categoryId), getPeriod(it.periodId))
        }
    }
}