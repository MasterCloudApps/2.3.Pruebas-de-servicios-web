function doSomething() {
    throw new Error("My error");
}

test('Exception Error should be thrown', () => {

    expect(() => {

        doSomething();

    }).toThrow();

});

test('Exception Error with "My error" should be thrown', () => {

    expect(() => {

        doSomething();

    }).toThrowError('My error');

});