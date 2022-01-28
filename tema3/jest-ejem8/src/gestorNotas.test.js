import BDAlumnos from './bDAlumnos';
import GestorNotas from './gestorNotas';

jest.mock('./bDAlumnos');

test('Cálculo nota media', () => {

    const getNotasAlumno = jest.fn().mockReturnValue([5, 6, 8, 9]);

    let mockAlumnos = { getNotasAlumno }

    BDAlumnos.mockImplementation(() => mockAlumnos);

    let gestorNotas = new GestorNotas();

    expect(gestorNotas.calculaNotaMedia(1)).toBeCloseTo(7);

    expect(mockAlumnos.getNotasAlumno).toBeCalledWith(1);

});