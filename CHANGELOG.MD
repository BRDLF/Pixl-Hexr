# EXPERIMENTAL BUILD

Trying to fix issues with resizing, maximizing/unmaximizing.
Currently, enlarging window updates minsize

## v0.1.2

##### Changes
-Added memory: grid will now remember which buttons are selected between updates/gridsize changes
-Added clear button: you can now clear entire grid with press of a button

#### Known Issues
-Resizing grid will break fullscreen
-Enlarging frame does not update minimumsize (enfocing minimum size on gridchange prevents frame from shrinking.)

#### ToDo
-Fix auto-resizing issues

## v0.1.1

##### Changes
-After fixing my math, 64 width arrays now work properly
-Syntax of output fixed to represent valid C++

##### Known Issues
-Resizing grid will break fullscreen
-Enlarging frame does not update minimumsize (enfocing minimum size on gridchange prevents frame from shrinking.)
-Grid clears on Frame change & on text update

##### ToDo
-Fix auto-resizing issues

### v0.1

##### Changes
-Radio buttons now adjust height/width of the grid accordingly.
-Update button stores central grid as series of hexcodes

##### Known Issues
-Resizing frame will break fullscreen
-Enlarging frame does not update minimumsize (enfocing minimum size on framechange prevents frame from shrinking.)
-Central grid clears on Frame change & on text update
-64 Width arrays don't work right. Something to do with sign bits. Need to implement bitmath instead of addition of powers

##### ToDo
-Fix above bugs