package com.example.quickity.repositories

import androidx.lifecycle.MutableLiveData
import com.example.quickity.dao.EmployeeDao
import com.example.quickity.models.Employee
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees = MutableLiveData<List<Employee>>()
    val foundEmployee = MutableLiveData<Employee>()
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    fun addEmployee(newEmployee: Employee) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.addEmployee(newEmployee)
        }
    }

    fun getAllEmployees() {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.getAllEmployees()
        }
    }

    fun updateEmployeeDetails(newEmployee: Employee) {
        coroutineScope.launch(Dispatchers.IO) {
            employeeDao.updateEmployeeDetails(newEmployee)
        }
    }


}
