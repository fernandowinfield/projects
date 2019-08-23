from dot import Dot


class Dots:
    """A collection of dots."""
    def __init__(self, WIDTH, HEIGHT,
                 LEFT_VERT, RIGHT_VERT,
                 TOP_HORIZ, BOTTOM_HORIZ):
        self.WIDTH = WIDTH
        self.HEIGHT = HEIGHT
        self.TH = TOP_HORIZ
        self.BH = BOTTOM_HORIZ
        self.LV = LEFT_VERT
        self.RV = RIGHT_VERT
        self.SPACING = 75
        self.EAT_DIST = 50
        # Initialize four rows of dots, based on spacing and width of the maze
        self.top_row = [Dot(self.SPACING * i, self.TH)
                        for i in range(self.WIDTH//self.SPACING + 1)]
        self.bottom_row = [Dot(self.SPACING * i, self.BH)
                           for i in range(self.WIDTH//self.SPACING + 1)]
        self.left_col = [Dot(self.LV, self.SPACING * i)
                         for i in range(self.HEIGHT//self.SPACING + 1)]
        self.right_col = [Dot(self.RV, self.SPACING * i)
                          for i in range(self.HEIGHT//self.SPACING + 1)]

    def display(self):
        """Calls each dot's display method"""
        for i in range(0, len(self.top_row)):
            self.top_row[i].display()
        for i in range(0, len(self.bottom_row)):
            self.bottom_row[i].display()
        for i in range(0, len(self.left_col)):
            self.left_col[i].display()
        for i in range(0, len(self.right_col)):
            self.right_col[i].display()

    def eat(self, x, y):
        """Based on the given location of Pac-Man, it removes the appropriate
        eaten dot(s) from the appropriate list of dots"""
        # If Pac-Man is on the bottom row:
        if y == self.BH:
            # Search through every dot in the row/column where Pac-Man is
            for dot in self.bottom_row:
                # Check if Pac-Man is within range to eat a dot
                if (dot.x in range(x-self.EAT_DIST, x+self.EAT_DIST+1) and
                   dot.y == y):
                    # Check for edge cases like dots at the edge of the maze...
                    if dot.x == self.WIDTH:
                        self.bottom_row.remove(dot)
                        self.bottom_row.pop(0)
                    elif dot.x == 0:
                        self.bottom_row.remove(dot)
                        self.bottom_row.pop()
                    # ...or like dots at an intersection
                    elif dot.x == self.LV:
                        self.bottom_row.remove(dot)
                        for double_dot in self.left_col:
                            if (double_dot.x == self.LV and double_dot.y ==
                               self.BH):
                                self.left_col.remove(double_dot)
                    elif dot.x == self.RV:
                        self.bottom_row.remove(dot)
                        for double_dot in self.right_col:
                            if (double_dot.x == self.RV and double_dot.y ==
                               self.BH):
                                self.right_col.remove(double_dot)
                    # otherwise, just eat the dot from such row/column
                    else:
                        self.bottom_row.remove(dot)
        # If Pac-Man is on the top row:
        elif y == self.TH:
            for dot in self.top_row:
                if (dot.x in range(x-self.EAT_DIST, x+self.EAT_DIST+1) and
                   dot.y == y):
                    if dot.x == self.WIDTH:
                        self.top_row.remove(dot)
                        self.top_row.pop(0)
                    elif dot.x == 0:
                        self.top_row.remove(dot)
                        self.top_row.pop()
                    elif dot.x == self.LV:
                        self.top_row.remove(dot)
                        for double_dot in self.left_col:
                            if (double_dot.x == self.LV and double_dot.y ==
                               self.TH):
                                self.left_col.remove(double_dot)
                    elif dot.x == self.RV:
                        self.top_row.remove(dot)
                        for double_dot in self.right_col:
                            if (double_dot.x == self.RV and double_dot.y ==
                               self.TH):
                                self.right_col.remove(double_dot)
                    else:
                        self.top_row.remove(dot)
        # If Pac-Man is on the left column:
        if x == self.LV:
            for dot in self.left_col:
                if dot.x == x and dot.y in range(y-self.EAT_DIST,
                                                 y+self.EAT_DIST+1):
                    if dot.y == self.HEIGHT:
                        self.left_col.remove(dot)
                        self.left_col.pop(0)
                    elif dot.y == 0:
                        self.left_col.remove(dot)
                        self.left_col.pop()
                    elif dot.y == self.BH:
                        self.left_col.remove(dot)
                        for double_dot in self.bottom_row:
                            if (double_dot.y == self.BH and double_dot.x ==
                               self.LV):
                                self.bottom_row.remove(double_dot)
                    elif dot.y == self.TH:
                        self.left_col.remove(dot)
                        for double_dot in self.top_row:
                            if (double_dot.y == self.TH and double_dot.x ==
                               self.LV):
                                self.top_row.remove(double_dot)
                    else:
                        self.left_col.remove(dot)
        # If Pac-Man is on the right column:
        elif x == self.RV:
            for dot in self.right_col:
                if dot.x == x and dot.y in range(y-self.EAT_DIST,
                                                 y+self.EAT_DIST+1):
                    if dot.y == self.HEIGHT:
                        self.right_col.remove(dot)
                        self.right_col.pop(0)
                    elif dot.y == 0:
                        self.right_col.remove(dot)
                        self.right_col.pop()
                    elif dot.y == self.BH:
                        self.right_col.remove(dot)
                        for double_dot in self.bottom_row:
                            if (double_dot.y == self.BH and double_dot.x ==
                               self.RV):
                                self.bottom_row.remove(double_dot)
                    elif dot.y == self.TH:
                        self.right_col.remove(dot)
                        for double_dot in self.top_row:
                            if (double_dot.y == self.TH and double_dot.x ==
                               self.RV):
                                self.top_row.remove(double_dot)
                    else:
                        self.right_col.remove(dot)

    def dots_left(self):
        """Returns the number of remaing dots in the collection"""
        return (len(self.top_row) +
                len(self.bottom_row) +
                len(self.left_col) +
                len(self.right_col))
