v0.1

Known Issues
-Resizing frame will break fullscreen
-Enlarging frame does not update minimumsize (enfocing minimum size on framechange prevents frame from shrinking.)
-Central grid clears on Frame change & on text update
-64 Width arrays don't work right. Something to do with sign bits. Need to implement bitmath instead of addition of powers

Changes
-Radio buttons now adjust height/width of the grid accordingly.
-Update button stores central grid as series of hexcodes

ToDo
-Fix above bugs