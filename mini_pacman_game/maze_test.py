from maze import Maze
from game_controller import GameController
from dots import Dots


def test_constructor():
    g = GameController(600, 400)
    m = Maze(600, 400, 150, 450,
             100, 300, g)
    assert m.LEFT_VERT == 150
    assert m.RIGHT_VERT == 450
    assert m.TOP_HORIZ == 100
    assert m.BOTTOM_HORIZ == 300
    assert m.WIDTH == 600
    assert m.HEIGHT == 400
    assert m.gc is g
    assert m.dots.dots_left() == ((m.dots.WIDTH//m.dots.SPACING + 1) * 2 +
                                  (m.dots.HEIGHT//m.dots.SPACING + 1) * 2)


def test_eat_dots():
    # Test eating a dot at the end of bottom row
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(600, 450)
    SPACING_multiple = 1
    for i in range(len(m.dots.bottom_row)):
        assert m.dots.bottom_row[i].x == m.dots.SPACING * SPACING_multiple
        assert m.dots.bottom_row[i].y == m.dots.BH
        SPACING_multiple += 1

    # Test eating a dot at the beginning of right column
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(450, 5)
    SPACING_multiple = 1
    for i in range(len(m.dots.right_col)):
        assert m.dots.right_col[i].x == m.dots.RV
        assert m.dots.right_col[i].y == m.dots.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the bottom left intersection
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(151, 450)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert m.dots.bottom_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.bottom_row[dot_i].y == m.dots.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert m.dots.left_col[dot_i].x == m.dots.LV
        assert m.dots.left_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom right intersection
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(450, 450)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert m.dots.bottom_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.bottom_row[dot_i].y == m.dots.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert m.dots.right_col[dot_i].x == m.dots.RV
        assert m.dots.right_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1

    # Test eating a dot at the top right intersection
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(450, 150)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert m.dots.top_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.top_row[dot_i].y == m.dots.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert m.dots.right_col[dot_i].x == m.dots.RV
        assert m.dots.right_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1

    # Test eating a dot at the top left intersection
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(150, 140)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert m.dots.top_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.top_row[dot_i].y == m.dots.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert m.dots.left_col[dot_i].x == m.dots.LV
        assert m.dots.left_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom row
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(300, 450)
    dot_i = 0
    for i in [0, 1, 2, 3, 5, 6, 7, 8]:
        assert m.dots.bottom_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.bottom_row[dot_i].y == m.dots.BH
        dot_i += 1

    # Test eating a dot at the top row
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(365, 150)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert m.dots.top_row[dot_i].x == m.dots.SPACING * i
        assert m.dots.top_row[dot_i].y == m.dots.TH
        dot_i += 1

    # Test eating a dot at the left column
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(150, 377)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert m.dots.left_col[dot_i].x == m.dots.LV
        assert m.dots.left_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1

    # Test eating a dot at the right column
    g = GameController(600, 600)
    m = Maze(600, 600, 150, 450,
             150, 450, g)
    m.eat_dots(450, 375)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert m.dots.right_col[dot_i].x == m.dots.RV
        assert m.dots.right_col[dot_i].y == m.dots.SPACING * i
        dot_i += 1
