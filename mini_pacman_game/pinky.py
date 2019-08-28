from game_character import GameCharacter
from eyes import Eyes


class Pinky(GameCharacter):
    def __init__(self, maze, pacman, game_controller):
        self.CHAR_WIDTH = 100
        self.CHAR_HEIGHT = 100
        self.maze = maze
        self.pacman = pacman
        self.gc = game_controller
        self.x = maze.WIDTH/2
        self.y = maze.TOP_HORIZ
        self.velocity = 2
        self.x_add = self.velocity
        self.y_add = 0
        self.eyes = Eyes()
        self.looking = (0, 0)
        self.WIN_PROXIMITY = 80
        self.WALL_TOLERANCE = 2
        self.maze_graph = [[0,300,300,float('inf'),float('inf')],
                           [300,0,float('inf'),300,float('inf')],
                           [300,float('inf'),0,300,float('inf')],
                           [float('inf'),300,300,0,float('inf')], 
                           [float('inf'),float('inf'),float('inf'),float('inf'),0]]

    def draw_self(self, x, y):
        """Draw Pinky to the screen"""
        noStroke()
        fill(1.0, 0.5, 0.6)
        ellipse(x, y, 100, 100)
        bottom_half = createShape()
        bottom_half.beginShape()
        bottom_half.vertex(x, y)
        bottom_half.vertex(x+100, y)
        bottom_half.vertex(x+100, y+50)
        bottom_half.vertex(x+50, y+25)
        bottom_half.vertex(x, y+50)
        bottom_half.endShape()
        shape(bottom_half, -50, 0)

        self.eyes.display(x, y - 15, self.looking)

    def update(self):
        """Carry out necessary updates for each frame before
        drawing to screen"""

        # Check whether Pinky is up or down/left or right of Pacman
        up_down_part = self.pacman.y - self.y
        left_right_part = self.pacman.x - self.x

        # Update Pinky's eyes to look at Pacman
        self.update_eyes(up_down_part, left_right_part)

        # If Pinky gets close to Pacman, tell the GameController
        # that Pinky wins
        if (abs(up_down_part) < self.WIN_PROXIMITY and
                abs(left_right_part) < self.WIN_PROXIMITY):
            self.gc.pinky_wins = True

        # If the player wins, stop moving Pinky
        if self.gc.player_wins:
            self.x_add = 0
            self.y_add = 0

        # Move Pinky if at an intersection:
        if self.x == self.maze.LEFT_VERT:
            if self.y >= self.maze.BOTTOM_HORIZ - self.WALL_TOLERANCE\
            and self.y <= self.maze.BOTTOM_HORIZ + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(2, self.dijkstras(2), up_down_part, left_right_part)
            elif self.y >= self.maze.TOP_HORIZ - self.WALL_TOLERANCE\
            and self.y <= self.maze.TOP_HORIZ + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(0, self.dijkstras(0), up_down_part, left_right_part)
        
        elif self.x == self.maze.RIGHT_VERT:
            if self.y >= self.maze.BOTTOM_HORIZ - self.WALL_TOLERANCE\
            and self.y <= self.maze.BOTTOM_HORIZ + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(3, self.dijkstras(3), up_down_part, left_right_part)
            elif self.y >= self.maze.TOP_HORIZ - self.WALL_TOLERANCE\
            and self.y <= self.maze.TOP_HORIZ + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(1, self.dijkstras(1), up_down_part, left_right_part)
        
        elif self.y == self.maze.BOTTOM_HORIZ:
            if self.x >= self.maze.LEFT_VERT - self.WALL_TOLERANCE\
            and self.x <= self.maze.LEFT_VERT + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(2, self.dijkstras(2), up_down_part, left_right_part)
            elif self.x >= self.maze.RIGHT_VERT - self.WALL_TOLERANCE\
            and self.x <= self.maze.RIGHT_VERT + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(3, self.dijkstras(3), up_down_part, left_right_part)
        
        elif self.y == self.maze.TOP_HORIZ:
            if self.x >= self.maze.LEFT_VERT - self.WALL_TOLERANCE\
            and self.x <= self.maze.LEFT_VERT + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(0, self.dijkstras(0), up_down_part, left_right_part)
            elif self.x >= self.maze.RIGHT_VERT - self.WALL_TOLERANCE\
            and self.x <= self.maze.RIGHT_VERT + self.WALL_TOLERANCE:
                self.update_graph()
                self.move_Pinky(1, self.dijkstras(1), up_down_part, left_right_part)
        
        self.x = self.x + self.x_add
        self.y = self.y + self.y_add

    def move_Pinky(self, current, next, up_down_part, left_right_part):
        # if there is a direct path to catch Pacman, go directly towards him
        if next == 4:
            if up_down_part == 0:
                if current == 2:
                    if left_right_part > 0:
                        self.x_add = self.velocity
                        self.y_add = 0
                    else:
                        self.x_add = -self.velocity
                        self.y_add = 0
                elif current == 3:
                    if left_right_part < 0:
                        self.x_add = -self.velocity
                        self.y_add = 0
                    else:
                        self.x_add = self.velocity
                        self.y_add = 0
                elif current == 0:
                    if left_right_part > 0:
                        self.x_add = self.velocity
                        self.y_add = 0
                    else:
                        self.x_add = -self.velocity
                        self.y_add = 0
                else:
                    if left_right_part < 0:
                        self.x_add = -self.velocity
                        self.y_add = 0
                    else:
                        self.x_add = self.velocity
                        self.y_add = 0
            
            else:
                if current == 2:
                    if up_down_part > 0:
                        self.x_add = 0
                        self.y_add = self.velocity
                    else:
                        self.x_add = 0
                        self.y_add = -self.velocity
                elif current == 3:
                    if up_down_part > 0:
                        self.x_add = 0
                        self.y_add = self.velocity
                    else:
                        self.x_add = 0
                        self.y_add = -self.velocity
                elif current == 0:
                    if up_down_part < 0:
                        self.x_add = 0
                        self.y_add = -self.velocity
                    else:
                        self.x_add = 0
                        self.y_add = self.velocity
                else:
                    if up_down_part < 0:
                        self.x_add = 0
                        self.y_add = -self.velocity
                    else:
                        self.x_add = 0
                        self.y_add = self.velocity
        # if there's no direct path to pacman, follow the shortest path
        else:
            if current == 0:
                if next == 1:
                    self.x_add = self.velocity
                    self.y_add = 0
                else:
                    self.x_add = 0
                    self.y_add = -self.velocity

            elif current == 1:
                if next == 0:
                    self.x_add = -self.velocity
                    self.y_add = 0
                else:
                    self.x_add = 0
                    self.y_add = -self.velocity

            elif current == 2:
                if next == 0:
                    self.x_add = 0
                    self.y_add = self.velocity
                else:
                    self.x_add = self.velocity
                    self.y_add = 0

            elif current == 3:
                if next == 1:
                    self.x_add = 0
                    self.y_add = self.velocity
                else:
                    self.x_add = -self.velocity
                    self.y_add = 0

    def update_graph(self):
        pacman_edges = [float('inf'),float('inf'),float('inf'),float('inf'),0]
        pacman_x = self.pacman.x
        pacman_y = self.pacman.y
        left_vert = self.maze.LEFT_VERT
        right_vert = self.maze.RIGHT_VERT
        bottom_horiz = self.maze.BOTTOM_HORIZ
        top_horiz = self.maze.TOP_HORIZ

        # Pacman is on bottom row
        if pacman_y == bottom_horiz:
            if pacman_x >= left_vert and pacman_x <= right_vert:
                pacman_edges[2] = pacman_x - left_vert
                pacman_edges[3] = right_vert - pacman_x
            elif pacman_x < left_vert:
                pacman_edges[2] = left_vert - pacman_x
                pacman_edges[3] = left_vert + (left_vert - pacman_edges[2])
            else:
                pacman_edges[3] = pacman_x - right_vert
                pacman_edges[2] = left_vert + (left_vert - pacman_edges[3])
        # Pacman is on top row
        elif pacman_y == top_horiz:
            if pacman_x >= left_vert and pacman_x <= right_vert:
                pacman_edges[0] = pacman_x - left_vert
                pacman_edges[1] = right_vert - pacman_x
            elif pacman_x < left_vert:
                pacman_edges[0] = left_vert - pacman_x
                pacman_edges[1] = left_vert + (left_vert - pacman_edges[0])
            else:
                pacman_edges[1] = pacman_x - right_vert
                pacman_edges[0] = left_vert + (left_vert - pacman_edges[1])
        # Pacman is on left column
        if pacman_x == left_vert:
            if pacman_y >= bottom_horiz and pacman_y <= top_horiz:
                pacman_edges[2] = pacman_y - bottom_horiz
                pacman_edges[0] = top_horiz - pacman_y
            elif pacman_y < bottom_horiz:
                pacman_edges[2] = bottom_horiz - pacman_y
                pacman_edges[0] = bottom_horiz + (bottom_horiz - pacman_edges[2])
            else:
                pacman_edges[0] = pacman_x - top_horiz
                pacman_edges[2] = bottom_horiz + (bottom_horiz - pacman_edges[0])
        # Pacman is on right column
        elif pacman_x == right_vert:
            if pacman_y >= bottom_horiz and pacman_y <= top_horiz:
                pacman_edges[3] = pacman_y - bottom_horiz
                pacman_edges[1] = top_horiz - pacman_y
            elif pacman_y < bottom_horiz:
                pacman_edges[3] = bottom_horiz - pacman_y
                pacman_edges[1] = bottom_horiz + (bottom_horiz - pacman_edges[3])
            else:
                pacman_edges[1] = pacman_x - top_horiz
                pacman_edges[3] = bottom_horiz + (bottom_horiz - pacman_edges[1])

        for i in range(len(pacman_edges) - 1):
            self.maze_graph[i][-1] = pacman_edges[i]
        
        self.maze_graph[-1] = pacman_edges

    def dijkstras(self, s):
        dist = [ float('inf') for _ in range(len(self.maze_graph)) ]
        parent = [ -1 for _ in range(len(self.maze_graph)) ]
        n = set()
        dist[s] = 0
        while len(n) != len(self.maze_graph):
            u = self.select_min(dist, n)
            n.add(u)
            for v in range(len(self.maze_graph)):
                if self.maze_graph[u][v] != float('inf'):
                    self.relax(dist, parent, u, v)
        next_destination = 4
        while parent[next_destination] != -1:
            next_destination = parent[next_destination]
        if next_destination == parent[4]:
            return 4
        next_destination = 4
        while parent[next_destination] != s:
            next_destination = parent[next_destination]
        return next_destination

    def select_min(self, dist, n):
        min_dist = float('inf')
        min_dist_vertex = -1
        for vertex in range(len(dist)):
            if vertex not in n and dist[vertex] < min_dist:
                min_dist = dist[vertex]
                min_dist_vertex = vertex
        return min_dist_vertex

    def relax(self, dist, parent, u, v):
        if dist[u] + self.maze_graph[u][v] < dist[v]:
            dist[v] = dist[u] + self.maze_graph[u][v]
            parent[v] = u


    def update_eyes(self, up_down_part, left_right_part):
        """Set positioning of Pinky's eyes based on Pac-Man's coordinates"""
        if up_down_part and abs(up_down_part) > 5:
            y = up_down_part/abs(up_down_part)
        else:
            y = 0
        if left_right_part and abs(left_right_part) > 5:
            x = left_right_part/abs(left_right_part)
        else:
            x = 0
        self.looking = (x, y)
