package nl.hu.fnt.gsos.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import nl.hu.fnt.gsos.service.Track;
import nl.hu.fnt.gsos.service.TrackServiceImpl;

@Path("/tracks")
public class showTrackService {
	TrackServiceImpl tsi = TrackServiceImpl.getTsi();
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	//@Path("/")
	public List<Track> getMsg0() {

		//String output = "<ul>";
		return tsi.getTracks();
//		for (Track t : tsi.getTracks()) {
//				output = output + "<li>" + t.toString() + "</li>";
//		}
//		output = output + "</ul>";
//		return Response.status(200).entity(output).build();

	}
	
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {

		String output = "<ul>";
		TrackServiceImpl tsi = new TrackServiceImpl();
		for (Track t : tsi.getTracks()) {
			if (t.getId() == Integer.parseInt(msg)) {
				output = output + "<li>" + t.toString() + "</li>";
			}
		}
		output = output + "</ul>";
		return Response.status(200).entity(output).build();

	}
	@PUT
    @Path("/{track_id}")
    public Response putTrackId(@PathParam("track_id")int track_id)
    {
        boolean added = false;
        List<Track> tracks = tsi.getTracks();
        if(tsi.getTrackById(track_id) == null) {
            tracks.add(new Track(track_id, "",
                    "", 2013,
                    ""));
            tsi.setTracks(tracks);
            added = true;
        }
        return Response.status(Response.Status.OK).entity(added ? "Track has been added" : "Track already existed").build();
    }
	@DELETE
    @Path("/{track_id}")
    public Response deleteTrackById(@PathParam("track_id")int track_id)
    {
        boolean deleted = false;
        if(tsi.getTrackById(track_id) != null)
        {
        	tsi.remove(track_id);
            deleted = true;
        }
        return Response.status(Response.Status.OK).entity(deleted ? "Track was deleted" : "Track was not found").build();
    }
}