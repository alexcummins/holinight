# api/views.py

from rest_framework import generics
from .serializers import PartySerializer
from .models import Party


class CreateView(generics.ListCreateAPIView):
    """This class defines the create behavior of our rest api."""
    queryset = Party.objects.all()
    serializer_class = PartySerializer

    def perform_create(self, serializer):
        """Save the post data when creating a new bucketlist."""
        serializer.save()



class DetailsView(generics.RetrieveUpdateDestroyAPIView):
        """This class handles the http GET, PUT and DELETE requests."""

        queryset = Party.objects.all()
        serializer_class = PartySerializer


