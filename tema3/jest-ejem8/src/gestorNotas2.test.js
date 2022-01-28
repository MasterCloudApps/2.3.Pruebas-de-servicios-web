import BDAlumnos from './bDAlumnos';
import GestorNotas from './gestorNotas';

jest.mock('./bDAlumnos');

test('Cálculo nota media', () => {

    BDAlumnos.mockImplementation(() =>
        ({ getNotasAlumno: jest.fn().mockReturnValue([5, 6, 8, 9]) })
    );

    let gestorNotas = new GestorNotas();
    let gestorNotas2 = new GestorNotas();

    expect(gestorNotas.calculaNotaMedia(1)).toBeCloseTo(7);

    expect(BDAlumnos.mock.results[0].value.getNotasAlumno).toBeCalledWith(1);
    expect(BDAlumnos.mock.results[1].value.getNotasAlumno).not.toBeCalled();

});