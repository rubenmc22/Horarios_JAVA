package com.cpu.sistema_horario_java.app.horas;

import java.util.List;

public interface BloqueHorarioService {

    BloqueHorario buscar(Long id);

    List<BloqueHorario> horasLibresPorCursoPorDia(Long idCurso, Integer dia);

    List<BloqueHorario> horasLibresPorDocentePorDia(Long idDocente, Integer dia);

    List<BloqueHorario> listar();

    BloqueHorario guardar(BloqueHorario bloqueHorario);

    BloqueHorario reemplazar(Long id, BloqueHorario bloqueHorario);

    void eliminar();

    void eliminar(Long id);

    default String integerToDiaString(Integer dia) {
        String _dia = "";
        switch (dia) {
            case 1:
                _dia = "LUNES";
                break;
            case 2:
                _dia = "MARTES";
                break;
            case 3:
                _dia = "MIERCOLES";
                break;
            case 4:
                _dia = "JUEVES";
                break;
            case 5:
                _dia = "VIERNES";
                break;
            case 6:
                _dia = "SABADO";
                break;
            case 7:
                _dia = "DOMINGO";
                break;
            default:
                _dia = "";
        }
        return _dia;

    }

}