package edu.neo

import edu.neo.`interface`.IPersona
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito

class ExampleUnitTest {

    val persona:IPersona = Mockito.mock(IPersona::class.java)

    @Before
    fun IniciarElementos(){
        Mockito.`when`(persona.validaterLongNumDoc(8)).thenReturn(true)
        Mockito.`when`(persona.deletePersona("1")).thenReturn(true)
    }

    @Test
    fun longitudNumDoc(){
        assertEquals(persona.validaterLongNumDoc(8),true)
    }

    @Test
    fun eliminarPersona(){
        assertEquals(persona.deletePersona("sdp"),false)
    }

}