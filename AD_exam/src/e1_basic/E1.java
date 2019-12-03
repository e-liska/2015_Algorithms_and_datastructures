package e1_basic;

import java.lang.reflect.Array;

import networkFlow.Network;
import networkFlow.Vertex;

/**
 * @author Eliska Kopecka
 *
 */
public class E1 {
	private Team[] teams;
	private Network<String> netOfMatches;
	private int chosenTeamPos = -1;

	public E1(String league, String selectedTeam) {
		teams = this.parseTeams(league, selectedTeam);
		netOfMatches = new Network<String>();
		this.createNetwork();
	}

	/**
	 * Returns whether the selected team is eliminated or not.
	 *
	 * @return true if team is eliminated, false if the team still has a chance
	 */
	public boolean isEliminated() {
		netOfMatches.fordFulkerson();
		if (netOfMatches.getMaxFlow() == this.matchesLeftWithoutChosen())
			return false;
		return true;

	}

	private Team[] parseTeams(String info, String selectedTeamName) {
		String teamsInfo[] = info.split("-");
		Team[] teams = new Team[teamsInfo.length];
		for (int i = 0; i < teamsInfo.length; i++) {
			String[] teamDetails = teamsInfo[i].split(" ");
			int[] gamesToPlay = new int[teamDetails.length - 3];
			for (int j = 3; j < teamDetails.length; j++) {
				gamesToPlay[j - 3] = Integer.parseInt(teamDetails[j]);
			}
			Team currentTeam = new Team(teamDetails[0],
					Integer.parseInt(teamDetails[1]),
					Integer.parseInt(teamDetails[2]), gamesToPlay);
			teams[i] = currentTeam;
			if (currentTeam.getName().equals(selectedTeamName)) {
				// chosenTeam = currentTeam;
				chosenTeamPos = i;
			}
		}
		return teams;
	}

	private void createNetwork() {
		// source and sinks
		Vertex<String> source = netOfMatches.createVertex("source");
		netOfMatches.setSource(source);
		Vertex<String> sink = netOfMatches.createVertex("sink");
		netOfMatches.setSink(sink);

		// teams
		int chosenTeamPoints = teams[chosenTeamPos].getPoints()
				+ (teams[chosenTeamPos].getNrOfGamesLeft() * 2);
		Vertex<String>[] verticesTeams = ((Vertex<String>[]) Array.newInstance(
				Vertex.class, teams.length));
		for (int i = 0; i < teams.length; i++) {
			if (i != chosenTeamPos) {
				Team team = teams[i];
				Vertex<String> currentTeam = netOfMatches.createVertex(team
						.getName());
				verticesTeams[i] = currentTeam;
				// edge from team to sink
				netOfMatches.createEdge(currentTeam, sink, chosenTeamPoints
						- team.getPoints());
			}
		}

		for (int i = 0; i < verticesTeams.length; i++) {
			if (i == chosenTeamPos) {
				continue;
			}
			for (int j = i + 1; j < verticesTeams.length; j++) {
				if (j == chosenTeamPos) {
					continue;
				}
				Vertex<String> currentTuple = netOfMatches
						.createVertex(verticesTeams[i].getElement() + "-"
								+ verticesTeams[j].getElement());
				// edges from tuple to a team
				netOfMatches.createEdge(currentTuple, verticesTeams[i],
						teams[i].getGamesToPlay()[j] * 2);
				netOfMatches.createEdge(currentTuple, verticesTeams[j],
						teams[i].getGamesToPlay()[j] * 2);

				// edge from a source to a tuple
				netOfMatches.createEdge(source, currentTuple,
						(teams[i].getGamesToPlay()[j] * 2));
				// newV=verticesTeams[i].getElement()+"-"+verticesTeams[j].getElement();
			}
		}
	}

	private int matchesLeftWithoutChosen() {
		int matches = 0;
		for (int i = 0; i < teams.length; i++) {
			if (i == chosenTeamPos) {
				matches -= teams[i].getNrOfGamesLeft();
			} else {
				matches += teams[i].getNrOfGamesLeft();
			}
		}
		return matches;
	}

}
