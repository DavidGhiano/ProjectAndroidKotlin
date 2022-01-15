package edu.neo.tpfinal

import edu.neo.tpfinal.interfaces.IUser
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val user: IUser = Mockito.mock(IUser::class.java)

    @Before
    fun initializateElements(){
        Mockito.`when`(user.validarUsuario("david","321")).thenReturn(true)
        Mockito.`when`(user.validarLongPass(6)).thenReturn(true)
    }


    @Test
    fun validar(){
        assertEquals(user.validarUsuario("david","321"),true)
    }

    @Test
    fun longitud(){
        assertEquals(user.validarLongPass(6),true)
    }
}