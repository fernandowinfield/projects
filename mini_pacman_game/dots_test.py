from dots import Dots
from pacman import Pacman


def test_constructor():
    """Test the constructor for Dots class"""
    ds = Dots(600, 600, 150, 450, 150, 450)
    assert ds.WIDTH == 600
    assert ds.HEIGHT == 600
    assert ds.TH == 150
    assert ds.BH == 450
    assert ds.LV == 150
    assert ds.RV == 450
    assert len(ds.bottom_row) == len(ds.top_row) == ds.WIDTH//ds.SPACING + 1
    assert len(ds.left_col) == len(ds.right_col) == ds.HEIGHT//ds.SPACING + 1
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH


def test_eat():
    """Test the eat method of Dots class"""
    # PAC-MAN at BOTTOM ROW TESTS:

    # Test eating a dot at the end of bottom row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 600
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.bottom_row[i].y == ds.BH
        SPACING_multiple += 1

    # Test eating a dot at the end of bottom row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 585
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.bottom_row[i].y == ds.BH
        SPACING_multiple += 1

    # Test eating a dot at the beginning of bottom row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 0
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.bottom_row[i].y == ds.BH
        SPACING_multiple += 1

    # Test eating a dot at the beginning of bottom row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 37
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.bottom_row[i].y == ds.BH
        SPACING_multiple += 1

    # Test eating a dot at the bottom left intersection
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom left intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 161
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom right intersection
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom right intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 443
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the bottom row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 375
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1

    # Test eating a dot at the bottom row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 365
    y = 450
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1

    # PAC-MAN at TOP ROW TESTS:

    # Test eating a dot at the end of top row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 600
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.top_row[i].y == ds.TH
        SPACING_multiple += 1

    # Test eating a dot at the end of top row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 585
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.top_row[i].y == ds.TH
        SPACING_multiple += 1

    # Test eating a dot at the beginning of top row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 0
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.top_row[i].y == ds.TH
        SPACING_multiple += 1

    # Test eating a dot at the beginning of top row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 37
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * SPACING_multiple
        assert ds.top_row[i].y == ds.TH
        SPACING_multiple += 1

    # Test eating a dot at the top left intersection
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the top left intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 161
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the top right intersection
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the top right intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 443
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the top row
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 375
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1

    # Test eating a dot at the top row
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 365
    y = 150
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1

    # PAC-MAN at LEFT COLUMN TESTS:

    # Test eating a dot at the end of left column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 600
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the end of left column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 585
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the beginning of left column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 0
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the beginning of left column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 37
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the top left intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 140
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1

    # Test eating a dot at the bottom left intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 428
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1

    # Test eating a dot at the left column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 375
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the left column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 150
    y = 365
    for i in range(len(ds.left_col)):
        assert ds.left_col[i].x == ds.LV
        assert ds.left_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.left_col[dot_i].x == ds.LV
        assert ds.left_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # PAC-MAN at RIGHT COLUMN TESTS:

    # Test eating a dot at the end of right column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 600
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the end of right column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 585
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the beginning of right column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 0
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the beginning of right column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 37
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    SPACING_multiple = 1
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * SPACING_multiple
        SPACING_multiple += 1

    # Test eating a dot at the top right intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 140
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    for i in range(len(ds.top_row)):
        assert ds.top_row[i].x == ds.SPACING * i
        assert ds.top_row[i].y == ds.TH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 3, 4, 5, 6, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.top_row[dot_i].x == ds.SPACING * i
        assert ds.top_row[dot_i].y == ds.TH
        dot_i += 1

    # Test eating a dot at the bottom right intersection
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 428
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    for i in range(len(ds.bottom_row)):
        assert ds.bottom_row[i].x == ds.SPACING * i
        assert ds.bottom_row[i].y == ds.BH
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 5, 7, 8]:
        assert ds.bottom_row[dot_i].x == ds.SPACING * i
        assert ds.bottom_row[dot_i].y == ds.BH
        dot_i += 1

    # Test eating a dot at the right column
    # (exactly at dot location)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 375
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1

    # Test eating a dot at the right column
    # (within Pac-Man eating range)
    ds = Dots(600, 600, 150, 450, 150, 450)
    x = 450
    y = 365
    for i in range(len(ds.right_col)):
        assert ds.right_col[i].x == ds.RV
        assert ds.right_col[i].y == ds.SPACING * i
    ds.eat(x, y)
    dot_i = 0
    for i in [0, 1, 2, 3, 4, 6, 7, 8]:
        assert ds.right_col[dot_i].x == ds.RV
        assert ds.right_col[dot_i].y == ds.SPACING * i
        dot_i += 1


def test_dots_left():
    ds = Dots(600, 600, 150, 450, 150, 450)
    dl = ds.dots_left()
    assert dl == ((ds.WIDTH//ds.SPACING + 1) * 2 +
                  (ds.HEIGHT//ds.SPACING + 1) * 2)
