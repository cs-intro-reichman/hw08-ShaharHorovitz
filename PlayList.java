/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if (this.size == this.maxSize)
        {
            return false; 
        }
        this.tracks[this.size] = new Track(track.getTitle(), track.getArtist(), track.getDuration()); //adds the track in the next availble index in the array
        this.size++; //updates the size of the playlist
        return true;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        StringBuilder track_data = new StringBuilder();
        track_data.append("\n");
        for (int i = 0; i<size; i++)
        {
            String title = tracks[i].getTitle();
            String artist = tracks[i].getArtist();
            int duration = tracks[i].getDuration();
            String song_data = artist + ", " + title + ", " + Integer.toString(duration);
            track_data.append(song_data);
            track_data.append("\n");
        }
        return track_data.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        this.size--; 
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        int duration_in_sec = 0;
        for (int i = 0; i<size; i++)
        {
            duration_in_sec += tracks[i].getDuration(); //gets the duration of each song in each tracks of the array

        }
        return duration_in_sec; //returns the total number od secounds
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        int index = -1;
        for (int i = 0; i<size; i++)
        {
            if (this.tracks[i].getTitle().equalsIgnoreCase(title))
            {
                return i; //if the name of the track in the playlist is equal to the given title, return it's index
            }
        }
        return index; //if there was no title in the pkaylist that is equal to the given title, return -1
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        if (i <0 || i>(this.size) || this.size == this.maxSize)
        {
            return false;
        }
        Track [] new_tracks = new Track[maxSize]; //assign a new array of tracks 
        if (i ==0) // if the index is the first one on the list
        {
            new_tracks[0] = new Track(track.getTitle(), track.getArtist(), track.getDuration());
            for (int r = 0; r<size; r++) //then putting all the tracks from the original playlist
            {
                new_tracks[r+1] = tracks[r]; 
            }
            this.tracks = new_tracks;
            size++;
            return true; 
        }
        if (i ==size) //then needs to add the track at the end of the tracks
        {
            this.tracks[this.size] = new Track(track.getTitle(), track.getArtist(), track.getDuration()); //adds the track in the next availble index in the array
            this.size++; //updates the size of the playlist
            return true;
        }
        int j= 0; 
        while(j<i) //as lons as the index of the original array is smaller then the given i, put the tracks in the same location
        {
            new_tracks[j] = tracks[j]; 
            j++;
        }
        if (j==i)
        {
            new_tracks[j] = new Track(track.getTitle(), track.getArtist(), track.getDuration());
            for (int r=j; r<size; r++)
            {
                new_tracks[r+1] = tracks[r]; //puts the track from the original array at the next location
            }
            this.tracks = new_tracks;
            this.size++;
            return true;
        }
        return false;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        Track [] new_tracks = new Track[maxSize]; //creates a new array of tracks
        if (i == 0) // if we need to remove the first element
        { 
            for (int r = 1; r<size; r++) //then putting all the tracks from the original playlist
            {
                new_tracks[r-1] = tracks[r]; //assign the original tracks to a new array, except of the first one
            }
            this.tracks = new_tracks;
            this.size--;
        }
        if (i == this.size) //if we need to remove the last track from the array 
        {
            for (int r = 0; r<size-1; r++) //then putting all the tracks from the original playlist
            {
                new_tracks[r] = tracks[r]; //assign the original tracks to a new array, except of the last one
            }
            this.tracks = new_tracks;
            this.size--;
        }
        int j = 0; 
        while (j < i)
        {
            new_tracks[j] = tracks[j]; 
            j++;
        }
        if (j ==i)
        {
            for (int s=j+1; s<size; s++)
            {
                new_tracks[s-1] = tracks[s]; //puts the track from the original array at the previous location
            }
            this.tracks = new_tracks; //updates the original track to be the new one
            this.size--;
        } 
        }

    

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        int index = indexOf(title); //checks what ts the index of the given title
        if (size > 0 && index >=0 && index <size)
        {
            remove(index);
        }
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() 
    {
        if (size >0)
        {
            Track [] new_tracks = new Track[maxSize];
            for (int i = 1; i<size; i++)
            {
                new_tracks[i-1] = tracks[i];
            }
            this.tracks = new_tracks;
            this.size--;
        }
        
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) 
    {
        if (other.getSize()+size <=maxSize)//first checks the size of both playlist and make sure it's not more then the max size
        {
            //Track [] tracks_to_add = other.tracks;
            for(int i = 0; i<other.getSize(); i++)
            {
                Track track_to_add = other.getTrack(i); //gets each track from the other playlist by it's index
                add(track_to_add); //add it to the current playlist
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        if (start<0 || start >size)
        {
            return -1;
        }
        int min = getTrack(start).getDuration(); //set the minimum value to be the duration of the first track in the playlist
        int index = start;
        for (int i = start+1; i<size; i++)
        {
            if (getTrack(i).getDuration() < min)
            {
                min = getTrack(i).getDuration();
                index = i;
            }
        }
        return index;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code
        //String title = titleOfShortestTrack();
        //int index = indexOf(title);
        //Track track_to_add = getTrack(index);
        //remove(title);
        //add(0,track_to_add); //first remove and add the min track at the beginig od the playlist
       //for (int i =1; i<size; i++)
        //{
            //int index_i = minIndex(i);
            //track_to_add = getTrack(index_i);
            //remove(index_i);
            //add(i,track_to_add);
        //}
        Track tempTrack = new Track("", "", 0);
        int minInd;
        for (int i = 0 ; i < this.size-1 ; i++)
        {
            minInd = minIndex(i);
            tempTrack=this.tracks[minInd];
            this.tracks[minInd] = this.tracks[i];
            this.tracks[i] = tempTrack;

        
        }}
}
