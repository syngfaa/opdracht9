package nl.hu.fnt.gsos.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import nl.hu.fnt.gsos.service.Track;
import nl.hu.fnt.gsos.service.TrackServiceImpl;

@Path("/count")
public class countTracksService {

	@GET
	public Response getMsg() {

		int counter = 0;
		TrackServiceImpl tsi = new TrackServiceImpl();
		for (Track t : tsi.getTracks()) {
			counter++;
		}
		String output = "There are currently "+ counter + " tracks in the list";
		return Response.status(200).entity(output).build();

	}

}