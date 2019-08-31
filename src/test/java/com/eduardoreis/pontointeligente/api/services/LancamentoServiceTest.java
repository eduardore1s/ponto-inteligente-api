package com.eduardoreis.pontointeligente.api.services;

import com.eduardoreis.pontointeligente.api.entities.Lancamento;
import com.eduardoreis.pontointeligente.api.repositories.LancamentoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

    @MockBean
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private  LancamentoService lancamentoService;

    @Before
    public void setUp() throws Exception{
        BDDMockito.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
                .willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
        BDDMockito.given(this.lancamentoRepository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(new Lancamento()));
        BDDMockito.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());
    }

    @Test
    public void testBuscarLancamentoPorFuncionario(){
        Page<Lancamento> lancamento = this.lancamentoService.buscarPorFuncionarioId(1L, new PageRequest(0,10));

        assertNotNull(lancamento);
    }

    @Test
    public void testBuscarLancamentoPorId(){
        Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);

        assertTrue(lancamento.isPresent());
    }

    @Test
    public void testPersistirLancamento(){
        Lancamento lancamento = this.lancamentoService.persistir(new Lancamento());

        assertNotNull(lancamento);
    }
}
