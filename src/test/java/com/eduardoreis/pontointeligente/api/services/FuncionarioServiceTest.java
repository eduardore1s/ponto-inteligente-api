package com.eduardoreis.pontointeligente.api.services;

import com.eduardoreis.pontointeligente.api.entities.Funcionario;
import com.eduardoreis.pontointeligente.api.repositories.FuncionarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {

    @MockBean
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private  FuncionarioService funcionarioService;

    private static final String CNPJ = "51463645000100";

    @Before
    public void setUp() throws Exception{
        BDDMockito.given(this.funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
        BDDMockito.given(this.funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(new Funcionario()));
        BDDMockito.given(this.funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
        BDDMockito.given(this.funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
    }

    @Test
    public void testPersistirFuncionario(){
        Funcionario funcionario = this.funcionarioService.persistir(new Funcionario());

        assertNotNull(funcionario);
    }

    @Test
    public void testBuscarFuncionarioPorId(){
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorId(1L);

        assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorEmail(){
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorEmail("email@email.com");

        assertTrue(funcionario.isPresent());
    }

    @Test
    public void testBuscarFuncionarioPorCpg(){
        Optional<Funcionario> funcionario = this.funcionarioService.buscarPorCpf("12546885981");

        assertTrue(funcionario.isPresent());
    }
}
